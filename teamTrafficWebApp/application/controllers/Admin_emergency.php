<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/*require APPPATH . '/libraries/REST_Controller.php';*/

class Admin_emergency extends CI_Controller {
    
    public function __construct()
    {
        parent::__construct();
        error_reporting(E_ALL);
		$this->load->model('Emergency_model');
    }
    
    /**
    * Emergency Contacts Module
    **/
    public function add_emergency()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']    =   'Traffic Police | Emergency Contacts';
        $data['heading']  =   'Emergency Contacts';
        $data['page_name']=   'admin/emergency-contacts/add-emergency-contact';
        $data['category'] =   $this->Emergency_model->get_category('emergency_categories');
        $data['division'] =   $this->Emergency_model->get_division('emergency_divisions');
   
        $data['districts']=   base_url('Admin_emergency/get_districts');
        $data['action']   =   base_url('Admin_emergency/add_emergency_process');
        
        $this->load->view('template', $data);
    }
    // get districts via jQuery ajax
    public function get_districts(){
        $id=$this->input->post('division_id');
        
        $data['districts']=$this->Emergency_model->get_districts($id);
       
        foreach($data['districts'] as $d)
        {
            echo '<option value='.$d->emergency_districts_id.'>'.$d->district_name.'</option>';
        }

        //$this->load->view('admin/emergency-contacts/districts',$data);
    }
    
    /* Insert data in Emergency Contacts tbl */
    public function add_emergency_process()
    {
        //echo 'add_emergency_process'; die;
        //error_reporting(1);
        $category = $this->input->post('category');
        $division = $this->input->post('division');
        $district = $this->input->post('district');
        
        $this->form_validation->set_rules('category', 'Select Category', 'required|callback_validate_category');
        $this->form_validation->set_rules('division', 'Select Division', 'required|callback_validate_division');
        $this->form_validation->set_rules('district', 'Select District', 'required|callback_validate_district');
        $this->form_validation->set_rules('name', 'Name', 'trim|required');
        $this->form_validation->set_rules('contact', 'Contact Number', 'trim|required|numeric');
        $this->form_validation->set_rules('lat', 'Latitude', 'trim|required|decimal');
        $this->form_validation->set_rules('lng', 'Longitude', 'trim|required|decimal');
        
        if ($this->form_validation->run() == FALSE){
            //redirect('Admin_emergency/add_emergency');
            // load view again
            $data['title']    =   'Traffic Police | Emergency Contacts';
            $data['heading']  =   'Emergency Contacts';
            $data['page_name']=   'admin/emergency-contacts/add-emergency-contact';
            $data['category'] =   $this->Emergency_model->get_category('emergency_categories');
            $data['division'] =   $this->Emergency_model->get_division('emergency_divisions');
            $data['districts']=   base_url('Admin_emergency/get_districts');
            $data['action']   =   base_url('Admin_emergency/add_emergency_process');

            $this->load->view('template', $data);
            
        }else{
            $data = array(
                'emergency_category_id'  => $this->input->post('category'),
                'emergency_district_id'  => $this->input->post('district'),
                'latitude'               => $this->input->post('lat'),
                'longitude'              => $this->input->post('lng'),
                'name'                   => $this->input->post('name'),
                'contact_no'             => $this->input->post('contact')
            );
            //print_r($data);
            /* Database Insertion */
            $this->Emergency_model->insert('emergency_contacts_detail', $data);
            $this->session->set_flashdata('msg','Record has been Submitted Succesfully!');
            redirect('Admin_emergency/emergency_list');
        }
    }
    
    public function emergency_list()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']    =   'Traffic Police | Emergency Contacts';
        $data['heading']  =   'Emergency Contacts';
        $data['page_name']=   'admin/emergency-contacts/emergency-contact-list';
        $data['data']	  =	  $this->Emergency_model->emergency_list();
        //echo '<pre>';print_r($data['data']); die;
        $this->load->view('template', $data);
    }
    
    public function edit_emergency_contact($id){
		// Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['id']       = $id;
        $data['title']    = 'Traffic Police | Emergency Contacts';
        $data['heading']  = 'Emergency Contacts';
        $data['page_name']= 'admin/emergency-contacts/edit-emergency-contact';
        $data['category'] =   $this->Emergency_model->get_category('emergency_categories');
        $data['division'] =   $this->Emergency_model->get_division('emergency_divisions');

        $data['districts']= base_url('Admin_emergency/get_districts');
        $data['record']   = $this->Emergency_model->get_record_by_id($id);
        //print_r($data['record']);
        $data['action']   = base_url('admin_emergency/edit_process');

        $this->load->view('template',$data);
	 }
	 
	 public function edit_process(){
		$id = $this->input->post('emergency_detail_id');
        // echo $id; die;
         
        $data = array(
            'emergency_category_id'  => $this->input->post('category'),
            'emergency_district_id'  => $this->input->post('district'),
            'latitude'               => $this->input->post('lat'),
            'longitude'              => $this->input->post('lng'),
            'name'                   => $this->input->post('name'),
            'contact_no'             => $this->input->post('contact')
        );
		
		$this->Emergency_model->edit($id,$data);
		$this->session->set_flashdata('msg','Record has been Updated Successfully!!');
		redirect('Admin_emergency/emergency_list');
	 }
    
    public function delete_emergency_contact($id)
    {
        $this->Emergency_model->delete('emergency_contacts_detail',$id);
		
		// Setting message for front end
		$this->session->set_flashdata('msg','Record has been Deleted Successfully!!');
		redirect('Admin_emergency/emergency_list');
    }
    
    // Below function is called for validating select option field.
    function validate_category($category)
    {
       if($category == 0){
        $this->form_validation->set_message('validate_category', 'Please Select Category.');
        return false;
        } else{
        // User picked something.
        return true;
        }
    }
    function validate_division($division)
    {
       if($division == 0){
        $this->form_validation->set_message('validate_division', 'Please Select Division.');
        return false;
        } else{
        return true;
        }
    }
    function validate_district($district)
    {
       if($district == 0){
        $this->form_validation->set_message('validate_district', 'Please Select District.');
        return false;
        } else{
        return true;
        }
    }
    
    /**
    *   DIVISIONs CRUD CONTROLLERS
    **/
    public function add_division()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']    =   'Traffic Police | Division';
        $data['heading']  =   'Emergency Contacts';
        $data['page_name']=   'admin/emergency-contacts/add-division';
        $data['action']   =   base_url('Admin_emergency/add_division_process');
        
        $this->load->view('template', $data);
    }
    
    public function add_division_process()
    {
        $div_name = $this->input->post('name');
        if($this->Emergency_model->check_division($div_name))
        {
            $this->session->set_flashdata('msg','This Division Name already exists in List. Try Another!');
		    redirect('Admin_emergency/add_division');
        }else{
            $data = array(
                'division_name'  => $this->input->post('name')
            );
            
        /* Database Insertion */
        $this->Emergency_model->insert_division('emergency_divisions', $data);
        $this->session->set_flashdata('msg','Division has Added Succesfully!');
		redirect('Admin_emergency/show_divisions');
        }
    }
    
    public function show_divisions()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']    =   'Traffic Police | Division';
        $data['heading']  =   'Emergency Contacts';
        $data['page_name']=   'admin/emergency-contacts/show_divisions';
        $data['data']	  =	  $this->Emergency_model->get_division('emergency_divisions');
        //echo '<pre>';print_r($data['data']);
        $this->load->view('template', $data);
    }
    
    public function edit_division($id){
		// Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['id']       = $id;
        $data['title']    = 'Traffic Police | Division';
        $data['heading']  = 'Emergency Contacts';
        $data['page_name']= 'admin/emergency-contacts/edit-division';
		$data['record']   = $this->Emergency_model->get_division_by_id($id);
		$data['action']   = base_url('admin_emergency/edit_division_process');
		 
		$this->load->view('template',$data);
	 }
	 
	 public function edit_division_process(){
		$id = $this->input->post('emergency_division_id');
        // echo $id; die;
         
        $data = array(
           'division_name'  => $this->input->post('name')
        );
		
		$this->Emergency_model->edit_division($id,$data);
		$this->session->set_flashdata('msg','Division has been Updated Successfully!!');
		redirect('Admin_emergency/show_divisions');
	 }
    
    public function delete_division($id)
    {
        $this->Emergency_model->delete_division('emergency_divisions',$id);
		
		// Setting message for front end
		$this->session->set_flashdata('msg','Division has Deleted Successfully!!');
		redirect('Admin_emergency/show_divisions');
    }
    
    /**
    *    DISTRICTs CRUD CONTROLLERS
    **/
    public function add_district()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']    =   'Traffic Police | District';
        $data['heading']  =   'Emergency Contacts';
        $data['page_name']=   'admin/emergency-contacts/add-district';
        $data['division'] =	  $this->Emergency_model->get_division('emergency_divisions');
        $data['action']   =   base_url('Admin_emergency/add_district_process');
        
        $this->load->view('template', $data);
    }
    
    public function add_district_process()
    {
        $district_name = $this->input->post('name');
        if($this->Emergency_model->check_district($district_name))
        {
            $this->session->set_flashdata('msg','This District Name already exists in List. Try Another!');
		    redirect('Admin_emergency/add_district');
        }else{
            $data = array(
                'emergency_division_id'  => $this->input->post('division'),
                'district_name'          => $this->input->post('name')
            );

            /* Database Insertion */
            $this->Emergency_model->insert_district('emergency_districts', $data);
            $this->session->set_flashdata('msg','District has Added Succesfully!');
            redirect('Admin_emergency/show_districts');
        }
    }
    
    public function show_districts()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']    =   'Traffic Police | District';
        $data['heading']  =   'Emergency Contacts';
        $data['page_name']=   'admin/emergency-contacts/show_districts';
        $data['data']	  =	  $this->Emergency_model->districts_list('emergency_districts');
        //echo '<pre>';print_r($data['data']);
        $this->load->view('template', $data);
    }
    
    public function edit_district($id){
		// Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['id']       = $id;
        $data['title']    = 'Traffic Police | District';
        $data['heading']  = 'Emergency Contacts';
        $data['page_name']= 'admin/emergency-contacts/edit-district';
        $data['division'] =   $this->Emergency_model->get_division('emergency_divisions');
		$data['record']   = $this->Emergency_model->get_district_by_id($id);
		$data['action']   = base_url('admin_emergency/edit_district_process');
		 
		$this->load->view('template',$data);
	 }
	 
	 public function edit_district_process(){
		$id = $this->input->post('emergency_district_id');
         
        $data = array(
           'emergency_division_id'  => $this->input->post('division'),
           'district_name'          => $this->input->post('name')
        );
		
		$this->Emergency_model->edit_district($id,$data);
		$this->session->set_flashdata('msg','District has been Updated Successfully!!');
		redirect('Admin_emergency/show_districts');
	 }
    
    public function delete_district($id)
    {
        $this->Emergency_model->delete_district('emergency_districts',$id);
		
		// Setting message for front end
		$this->session->set_flashdata('msg','District has Deleted Successfully!!');
		redirect('Admin_emergency/show_districts');
    }
    
}