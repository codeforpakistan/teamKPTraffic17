<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Admin extends CI_Controller {
    
    public function __construct()
    {
        parent::__construct();
        error_reporting(E_ALL);
		$this->load->model('Admin_model');
    }
    
    public function index()
	{
        $admin_id = $this->session->userdata('admin_id');
        if($admin_id != NULL){
            redirect('admin/login');
        }
        
        $data['title'] = 'Traffic Police | Login';
        //$data['action']	   = base_url('admin/login');
		$this->load->view('admin/login',$data);
	}
    
    /***
    * Login Process
    ***/
    public function login()
	{
		$this->form_validation->set_rules('admin_username' , 'Username' , 'required|alpha|trim');
		$this->form_validation->set_rules('admin_password' , 'Password' , 'required');
		$this->form_validation->set_error_delimiters('<div class="text-danger">', '</div>');
        
        if ($this->input->post('login')) 
        {
            if($this->form_validation->run() == true)
		    {
			$username = $this->input->post('admin_username');
			$password = $this->input->post('admin_password');
			
			$user_name = $this->Admin_model->validate_login($username , $password);
                if($user_name)
                {
				 $user = $user_name->admin_name;
				 $login_id = $user_name->admin_id;
				
                 $this->session->set_userdata("admin_name" , $user);
                 $this->session->set_userdata("admin_id" , $login_id);
                 redirect('admin/dashboard');
                }
                else{
                    $this->session->set_flashdata("danger", " <div class='alert alert-danger text-center'>User name or Password not Matched !</div>");
				    redirect('admin/login');
                }
			}
			else
			{
				//echo $username;
				$this->session->set_flashdata("danger", " <div class='alert alert-danger text-center'>You must Login First!</div>");
				redirect('admin/login');
			}
        } else {
            $data['title'] = 'Traffic Police | Login';
            $this->load->view('admin/login', $data);
        }
	}
    
    public function logout()
	{
		$this->session->sess_destroy();
		$this->session->set_flashdata("success", " <div class='alert alert-success text-center'>You have logout successfully!</div>");
		return redirect('admin'); 
	}
    
    /**
    * Main Page COUNTS (Dashboard)
    **/
    public function dashboard()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']          =  'Traffic Police | Dashboard';
        $data['heading']        =  'Dashboard';
        $data['page_name']      =  'admin/index';
        $data['all_complaints']	=	$this->Admin_model->get_all_complaints('complaints');
        $data['completed']	    =	$this->Admin_model->completed_complaints('complaints');
        $data['in_progress']    =	$this->Admin_model->in_progress_complaints('complaints');
        $data['pending']        =	$this->Admin_model->pending_complaints('complaints');
        
        $data['live_updates']   = $this->Admin_model->live_updates('route_updates');
        $data['total_routes']   = $this->Admin_model->total_routes('routes');
        
        $data['traffic_signs']  = $this->Admin_model->traffic_signs('traffic_education');
        
        $data['total_contacts'] = $this->Admin_model->total_emergency_counts('emergency_contacts_detail');
        $data['total_divisions']= $this->Admin_model->total_divisions('emergency_divisions');
        $data['total_districts']= $this->Admin_model->total_districts('emergency_districts');
        $data['rescue_records'] = $this->Admin_model->total_rescue_records('emergency_contacts_detail');
        $data['health_records'] = $this->Admin_model->total_health_records('emergency_contacts_detail');
        $data['mechanics_records'] = $this->Admin_model->total_mechanics_records('emergency_contacts_detail');
        $data['highway_records'] = $this->Admin_model->total_highway_records('emergency_contacts_detail');
        
        $data['license_records'] = $this->Admin_model->total_license_counts('license_verification');
        $data['verified_licenses'] = $this->Admin_model->verified_licenses('license_verification');
        $data['expired_licenses'] = $this->Admin_model->expired_licenses('license_verification');
        //echo '<pre>';print_r($data['traffic_signs']);
        //echo 'Dashboard here.';
        $this->load->view('template', $data);
    }
    
    /**
    * Traffic Education Module
    **/
    public function add_traffic_sign()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']    =   'Traffic Police | Traffic Education';
        $data['heading']  =   'Traffic Education';
        $data['page_name']=   'admin/traffic-education/add-traffic-education';
        $data['action']   =   base_url('admin/add_traffic_sign_process');
        //echo '<pre>';print_r($data['action']);
        //echo 'Dashboard here.';

        $this->load->view('template', $data);
    }
    
    /* Insert data in traffic_education tbl */
    public function add_traffic_sign_process()
    {
        //echo 'add_traffic_sign_process'; die;
        
        // For Image
        $config = array(
            'upload_path'   =>'uploads/traffic-education',
            'allowed_types' =>'jpg|jpeg|png|gif',
            'max_size'      =>'4040KB'
            );

        $this->load->library('upload',$config);
        $this->upload->initialize($config);
        // End Image
        
        if($this->upload->do_upload('image')){
            $image = $this->upload->data('file_name');
        }
        else{
            $image = $this->upload->display_errors();
        }
        
        $data = array(
            'image'                  => $image,
            'image_title'            => $this->input->post('image_title'),
            'image_description_eng'  => $this->input->post('image_description_eng'),
            'image_description_urdu' => $this->input->post('image_description_urdu')
        );
        
        /* Database Insertion */
        $this->Admin_model->insert('traffic_education', $data);
        $this->session->set_flashdata('msg','Record has been Submitted Succesfully!');
		redirect('admin/view_all_signs');
       
    }
    
    public function view_all_signs()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | Traffic Education';
        $data['heading']    =   'Traffic Education';
        $data['page_name']  =   'admin/traffic-education/traffic-education-list';
        $data['data']	    =	$this->Admin_model->select_all_signs('traffic_education');
        //echo '<pre>';print_r($data['data']);
        //echo 'Dashboard here.';
        $this->load->view('template', $data);
    }
    
    public function edit_traffic_sign($id){
		// Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['id']       = $id;
        $data['title']    = 'Traffic Police | Traffic Education';
        $data['heading']  = 'Traffic Education';
        $data['page_name']= 'admin/traffic-education/edit-traffic-education';
		$data['record']   = $this->Admin_model->get_record_by_id($id);
		$data['action']   = base_url('admin/edit_process');
		 
		$this->load->view('template',$data);
	 }
	 
	 public function edit_process(){
		$id = $this->input->post('traffic_education_id');
         
        // echo $id; die;
         // For Image
        $config = array(
            'upload_path'   =>'uploads/traffic-education',
            'allowed_types' =>'jpg|jpeg|png|gif',
            'max_size'      =>'4040KB'
            );

        $this->load->library('upload',$config);
        $this->upload->initialize($config);
        // End Image
         
        $this->db->where('traffic_education_id',$id);
        $query=$this->db->get('traffic_education');
        $chk=$query->row();
        
        if(!$this->upload->do_upload('image')){
            //$error = array('error' => $this->upload->display_errors());
            $image=$chk->image;
        }
        else{
            $image = $this->upload->data('file_name');
        }
            
        $data = array(
            'image'                  => $image,
            'image_title'            => $this->input->post('image_title'),
            'image_description_eng'  => $this->input->post('image_description_eng'),
            'image_description_urdu' => $this->input->post('image_description_urdu')
            );
		//print_r($data); die; 
		$this->Admin_model->edit($id,$data);
		$this->session->set_flashdata('msg','Record has been Updated Successfully!!');
		redirect('admin/view_all_signs');
	 }
    
    public function delete_sign($id)
    {
        $this->Admin_model->delete('traffic_education',$id);
		
		// Setting message for front end
		$this->session->set_flashdata('msg','Record has been Deleted Successfully!!');
		redirect('admin/view_all_signs');
    }
    
/************************************ Traffic Education Module END ************************************************/
    
    /**
    * Complaint Module
    **/
    public function get_complaints()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | Complaints';
        $data['heading']    =   'Complaints';
        $data['page_name']  =   'admin/complaints/complaints-list';
        $data['data']	    =	$this->Admin_model->get_complaints('complaints');
        //echo '<pre>';print_r($data['data']);
        //echo 'Dashboard here.';
        $this->load->view('template', $data);
    }
    
    public function completed_complaints()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | Complaints';
        $data['heading']    =   'Complaints';
        $data['page_name']  =   'admin/complaints/completed-list';
        $data['data']	    =	$this->Admin_model->completed_complaints_list('complaints');
        //echo '<pre>';print_r($data['data']);
        //echo 'Dashboard here.';
        $this->load->view('template', $data);
    }
    
    public function inprogress_complaints()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | Complaints';
        $data['heading']    =   'Complaints';
        $data['page_name']  =   'admin/complaints/inprogress-list';
        $data['data']	    =	$this->Admin_model->inprogress_complaints_list('complaints');
        //echo '<pre>';print_r($data['data']);
        //echo 'Dashboard here.';
        $this->load->view('template', $data);
    }
    
    public function pending_complaints()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | Complaints';
        $data['heading']    =   'Complaints';
        $data['page_name']  =   'admin/complaints/pending-list';
        $data['data']	    =	$this->Admin_model->pending_complaints_list('complaints');
        //echo '<pre>';print_r($data['data']);
        //echo 'Dashboard here.';
        $this->load->view('template', $data);
    }
    public function delete_complaint($id)
    {
        $this->Admin_model->delete_complaint('complaints',$id);
		
		// Setting message for front end
		$this->session->set_flashdata('msg','Complaint has been Deleted Successfully!!');
		redirect('admin/get_complaints');
    }
    public function edit_complaint($id)
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['id']       = $id;
        $data['title']    = 'Traffic Police | Complaints';
        $data['heading']  = 'Complaints';
        $data['page_name']= 'admin/complaints/edit-complaint';
		$data['record']   = $this->Admin_model->get_record($id);
        $data['types']    = $this->Admin_model->get_complaint_types();
        $data['status']   = $this->Admin_model->get_complaint_status();
		$data['action']   = base_url('admin/complaint_process');
		 
		$this->load->view('template',$data);
    }
    public function complaint_process()
    {
        $id = $this->input->post('complaint_id');
         
        // echo $id; die;
         // For Image
        $config = array(
            'upload_path'   =>'uploads/images',
            'allowed_types' =>'jpg|jpeg|png|gif',
            'max_size'      =>'4040KB'
            );

        $this->load->library('upload',$config);
        $this->upload->initialize($config);
        // End Image
        
        $this->db->where('complaint_id',$id);
        $query=$this->db->get('complaints');
        $chk=$query->row();
        
        if(!$this->upload->do_upload('image')){
            // upload old image, load it from database
            $image=$chk->image;
        }
        else{
            $image = $this->upload->data('file_name');
        }
         
        $data = array(
            //'complaint_type_id'      => $this->input->post('type'),
            'complaints_status_id'   => $this->input->post('status'),
           // 'latitude'               => $this->input->post('lat'),
            //'longitude'              => $this->input->post('lng'),
           // 'description'            => $this->input->post('description'),
            //'image'                  => $image,
            //'video'                  => $video
            );
		//print_r($data); die; 
		$this->Admin_model->update_complaint($id,$data);
		$this->session->set_flashdata('msg','Record has been Updated Successfully!!');
		redirect('admin/get_complaints');
    }
    
    public function map()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | Complaints';
        $data['heading']    =   'Complaints Heat Map';
        $data['page_name']  =   'admin/complaints/map';
        $data['action']     =   site_url().'admin/map/';

        $results = $this->Admin_model->get_complaints('complaints');
        //echo '<pre>';print_r($results);die;
        $count = 0;
		
        foreach($results as $row)
        {
            $data['latlong'][$count]['latitude'] = $row->latitude;
			$data['latlong'][$count]['longitude'] = $row->longitude;
            $data['latlong'][$count]['complaint_type'] = $row->complaint_type;
            $data['latlong'][$count]['status'] = $row->status;
			$data['latlong'][$count]['description'] = $row->description;
			$data['latlong'][$count]['image'] = $row->image;
			$data['latlong'][$count]['video'] = $row->video;
			$data['latlong'][$count]['dated'] = $row->dated;
            $count++;
        }
        $data['map']    =   $count;

        //echo '<pre>';print_r($data['map']);
        //echo 'Map here.';
        $this->load->view('template', $data);
    }
        
    /******************************************** Complaints Module END ***************************************************/
    
    /**
    * License Verification Module
    **/
    public function get_license_list()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | License Verification';
        $data['heading']    =   'License Verification';
        $data['page_name']  =   'admin/license_verification/license_verification-list';
        $data['data']	    =	$this->Admin_model->select_all_records('license_verification');
        //echo '<pre>';print_r($data['data']); die;
        $this->load->view('template', $data);
    }
    
    public function add_license()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | License Verification';
        $data['heading']    =   'License Verification';
        $data['page_name']  =   'admin/license_verification/add-license';
        $data['lic_type']   =	$this->Admin_model->get_lic_types('license_types');
        $data['districts']  =   $this->Admin_model->get_lic_districts('license_districts');
        $data['action']	    =	base_url('admin/add_license_process');
        //echo '<pre>';print_r($data['lic_type']);
        $this->load->view('template', $data);
    }
     public function add_license_process()
     {
        $makerValue = $_POST['lic_type']; // make value
        $maker = $_POST['selected_text']; // get the selected text
        //$maker = mysql_real_escape_string($_POST['selected_text']); // get the selected text
         
        $districtValue = $_POST['district']; // make value
        $district = $_POST['select_district']; // get the selected text
        //$district = mysql_real_escape_string($_POST['select_district']); // get the selected text
         //echo $maker;die;
         
        $this->form_validation->set_rules('lic_number', 'Driving License Number', 'trim|required|max_length[12]|numeric');
        $this->form_validation->set_rules('cnic', 'Cnic', 'trim|required|max_length[15]|alpha_dash');
        $this->form_validation->set_rules('name', 'Name', 'trim|required');
        $this->form_validation->set_rules('father_name', 'Father Name', 'trim|required');
        $this->form_validation->set_rules('lic_type', 'License Type', 'trim|required|callback_validate_type');
        $this->form_validation->set_rules('lic_expiry_date', 'License Expiry Date', 'trim|required');
        $this->form_validation->set_rules('district', 'Select District', 'trim|required|callback_validate_district');
         
        if ($this->form_validation->run() == FALSE){
            $data['title']      =   'Traffic Police | License Verification';
            $data['heading']    =   'License Verification';
            $data['page_name']  =   'admin/license_verification/add-license';
            $data['lic_type']   =	$this->Admin_model->get_lic_types('license_types');
            $data['districts']  =   $this->Admin_model->get_lic_districts('license_districts');
            $data['action']	    =	base_url('admin/add_license_process');
            
            $this->load->view('template', $data);
        }else{
            $cnic = $this->input->post('cnic');
            
             $data = array(
                'dl_number'          => $this->input->post('lic_number'),
                'cnic'               => str_replace("-","",$cnic),
                'name'               => $this->input->post('name'),
                'father_name'        => $this->input->post('father_name'),
                'license_type'       => $maker,
                'license_expiry_date'=> $this->input->post('lic_expiry_date'),
                'district_id'        => $district,
                'lic_status'         => 1
            );
            //print_r($data);die;
            /* Database Insertion */
            $this->Admin_model->insert('license_verification', $data);
            $this->session->set_flashdata('msg','Record has been Submitted Succesfully!');
            redirect('admin/add_license');
        }
     }
    // Below function is called for validating select option field.
    function validate_type($maker)
    {
       if($maker == 0){
        $this->form_validation->set_message('validate_type', 'Please Select License Type.');
        return false;
        } else{
        // User picked something.
        return true;
        }
    }
    function validate_district($district)
    {
       if($district == 0){
        $this->form_validation->set_message('validate_district', 'Please Select District.');
        return false;
        } else{
        // User picked something.
        return true;
        }
    }
    
    public function edit_license($id)
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['id']         =   $id;
        $data['title']      =   'Traffic Police | License Verification';
        $data['heading']    =   'License Verification';
        $data['page_name']  =   'admin/license_verification/edit-license';
        $data['lic_type']	=	$this->Admin_model->get_lic_types('license_types');
        $data['districts']  =   $this->Admin_model->get_lic_districts('license_districts');
		$data['record']     =   $this->Admin_model->get_license_by_id($id);
        $data['action']	    =	base_url('admin/edit_license_process');
        //echo '<pre>';print_r($data['record']);
        $this->load->view('template', $data);
    }
    
    public function edit_license_process()
    {
        $id = $this->input->post('lic_id');
        $lic_status = $this->input->post('lic_expiry_date');
        
        /*$makerValue = $_POST['lic_type']; // make value
        $maker = mysql_escape_string($_POST['selected_text']); // get the selected text
        //echo $makerValue;die;
        $districtValue = $_POST['district']; // make value
        $district = mysql_escape_string($_POST['select_district']); // get the selected text*/
        //echo $districtValue;
        
        $this->form_validation->set_rules('lic_number', 'Driving License Number', 'trim|required|exact_length[12]|numeric');
        $this->form_validation->set_rules('cnic', 'Cnic', 'trim|required|exact_length[13]|numeric');
        $this->form_validation->set_rules('name', 'Name', 'trim|required');
        $this->form_validation->set_rules('father_name', 'Father Name', 'trim|required');
        //$this->form_validation->set_rules('lic_type', 'License Type', 'trim|required|callback_validate_type');
        $this->form_validation->set_rules('lic_expiry_date', 'License Expiry Date', 'trim|required');
        //$this->form_validation->set_rules('district', 'Select District', 'trim|required|callback_validate_district');
         
        if ($this->form_validation->run() == FALSE){
            $data['id']         =   $id;
            $data['title']      =   'Traffic Police | License Verification';
            $data['heading']    =   'License Verification';
            $data['page_name']  =   'admin/license_verification/edit-license';
            $data['lic_type']	=	$this->Admin_model->get_lic_types('license_types');
            $data['districts']  =   $this->Admin_model->get_lic_districts('license_districts');
            $data['record']     =   $this->Admin_model->get_license_by_id($id);
            $data['action']	    =	base_url('admin/edit_license_process');
            
            $this->load->view('template', $data);
            
        }else if(date('Y-m-d') <= $lic_status)
        {
            $data = array(
                'dl_number'             => $this->input->post('lic_number'),
                'cnic'                  => $this->input->post('cnic'),
                'name'                  => $this->input->post('name'),
                'father_name'           => $this->input->post('father_name'),
                'license_type'          => $this->input->post('lic_type'),
                'license_expiry_date'   => $this->input->post('lic_expiry_date'),
                'district_id'           => $this->input->post('district'),
                'lic_status'            => 1
                );
            //echo '<pre>';print_r($data); die;
            $this->Admin_model->update_license($id,$data);
            $this->session->set_flashdata('msg','Record has been Updated Successfully!!');
            redirect('admin/get_license_list');
        }else{
            $data = array(
                'dl_number'             => $this->input->post('lic_number'),
                'cnic'                  => $this->input->post('cnic'),
                'name'                  => $this->input->post('name'),
                'father_name'           => $this->input->post('father_name'),
                'license_type'          => $this->input->post('lic_type'),
                'license_expiry_date'   => $this->input->post('lic_expiry_date'),
                'district_id'           => $this->input->post('district'),
                'lic_status'            => 0
                );
            //print_r($data); die;
            $this->Admin_model->update_license($id,$data);
            $this->session->set_flashdata('msg','Record has been Updated Successfully!!');
            redirect('admin/get_license_list');
        }
    }
    
    public function get_lic_districts()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | License Verification';
        $data['heading']    =   'License Verification';
        $data['page_name']  =   'admin/license_verification/license-districts-list';
        $data['data']	    =	$this->Admin_model->districts_list('license_districts');
        //echo '<pre>';print_r($data['data']);
        $this->load->view('template', $data);
    }
    
    public function add_lic_district()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | License Verification';
        $data['heading']    =   'License Verification';
        $data['page_name']  =   'admin/license_verification/add-license-district';
        $data['action']	    =	base_url('admin/add_district_process');
        //echo '<pre>';print_r($data['data']);
        $this->load->view('template', $data);
    }
     public function add_district_process()
     {
        $dis = $this->input->post('name');
        if($this->Admin_model->check_dis($dis))
        {
            $this->session->set_flashdata('msg','This District already exists. Try Another!');
		    redirect('Admin/add_lic_district');
        }else{
             $data = array(
                'name'  => $this->input->post('name')
            );

            /* Database Insertion */
            $this->Admin_model->insert('license_districts', $data);
            $this->session->set_flashdata('msg','Record has been Submitted Succesfully!');
            redirect('admin/get_lic_districts');
        }
     }
    
    public function edit_lic_district($id)
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['id']         =   $id;
        $data['title']      =   'Traffic Police | License Verification';
        $data['heading']    =   'License Verification';
        $data['page_name']  =   'admin/license_verification/edit-license-district';
		$data['record']     =   $this->Admin_model->lic_district_by_id($id);
        $data['action']	    =	base_url('admin/edit_lic_district_process');
        //echo '<pre>';print_r($data['data']);
        $this->load->view('template', $data);
    }
    public function edit_lic_district_process()
    {
        $id = $this->input->post('district_id');
         
        $data = array(
            'name'   => $this->input->post('name')
            );
		
		$this->Admin_model->update_license_district($id,$data);
		$this->session->set_flashdata('msg','Record has been Updated Successfully!!');
		redirect('admin/get_lic_districts');
    }
    
    public function delete_lic_district($id)
    {
        $this->Admin_model->delete_lic_district('license_districts',$id);
		
		// Setting message for front end
		$this->session->set_flashdata('msg','Record has been Deleted Successfully!!');
		redirect('admin/get_lic_districts');
    }
    
    public function get_lic_types()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | License Verification';
        $data['heading']    =   'License Verification';
        $data['page_name']  =   'admin/license_verification/license-types-list';
        $data['data']	    =	$this->Admin_model->types_list('license_types');
        //echo '<pre>';print_r($data['data']);
        $this->load->view('template', $data);
    }
    
    public function add_lic_type()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | License Verification';
        $data['heading']    =   'License Verification';
        $data['page_name']  =   'admin/license_verification/add-license-type';
        $data['action']	    =	base_url('admin/add_type_process');
        //echo '<pre>';print_r($data['data']);
        $this->load->view('template', $data);
    }
     public function add_type_process()
     {
        $type = $this->input->post('lic_type');
        if($this->Admin_model->check_type($type))
        {
            $this->session->set_flashdata('msg','This License Type already exists. Try Another!');
		    redirect('Admin/add_lic_type');
        }else{
             $data = array(
                'license_type'  => $this->input->post('lic_type')
            );
        
        /* Database Insertion */
        $this->Admin_model->insert('license_types', $data);
        $this->session->set_flashdata('msg','Record has been Submitted Succesfully!');
		redirect('admin/get_lic_types');
        }
     }
    
    public function edit_lic_type($id)
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['id']         =   $id;
        $data['title']      =   'Traffic Police | License Verification';
        $data['heading']    =   'License Verification';
        $data['page_name']  =   'admin/license_verification/edit-license-type';
		$data['record']     =   $this->Admin_model->lic_type_by_id($id);
        $data['action']	    =	base_url('admin/edit_lic_type_process');
        //echo '<pre>';print_r($data['data']);
        $this->load->view('template', $data);
    }
    public function edit_lic_type_process()
    {
        $id = $this->input->post('type_id');
         
        $data = array(
            'license_type'   => $this->input->post('lic_type')
            );
		
		$this->Admin_model->update_license_type($id,$data);
		$this->session->set_flashdata('msg','Record has been Updated Successfully!!');
		redirect('admin/get_lic_types');
    }
    
    public function delete_lic_type($id)
    {
        $this->Admin_model->delete_lic_type('license_types',$id);
		
		// Setting message for front end
		$this->session->set_flashdata('msg','Record has been Deleted Successfully!!');
		redirect('admin/get_lic_types');
    }
    
    /************************************ License Verification Module END ***************************************************/
    
    /**
    * Live Updates Module
    **/
    public function add_route_update()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | Live Traffic Updates';
        $data['heading']    =   'Live Traffic Updates';
        $data['page_name']  =   'admin/live-updates/add-route-update';
        $data['route']      =   $this->Admin_model->get_route('routes');
        $data['status']     =   $this->Admin_model->get_route_status('route_status');
        $data['action']	    =	base_url('admin/update_route_status');
        
        $this->load->view('template', $data);
    }
    public function update_route_status()
    {
        $data = array(
            'route_id'          => $this->input->post('route'),
            'route_status_id'   => $this->input->post('status'),
            'updated_time'      => $this->input->post('time')
        );
        
        /* Database Insertion */
        $this->Admin_model->insert('route_updates', $data);
        $this->session->set_flashdata('msg','Record has been Submitted Succesfully!');
		redirect('admin/routes_list');
    }
    
    public function routes_list()
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['title']      =   'Traffic Police | Live Traffic Updates';
        $data['heading']    =   'Live Traffic Updates';
        $data['page_name']  =   'admin/live-updates/routes-list';
        $data['data']	    =	$this->Admin_model->select_all_routes('route_updates');
        
        $this->load->view('template', $data);
    }
    
    public function edit_route($id)
    {
        // Prevent from Direct Access
        if (!isset($_SESSION['admin_id'])) {
            redirect('admin/login');
        }
        $data['id']         =   $id;
        $data['title']      =   'Traffic Police | Live Traffic Updates';
        $data['heading']    =   'Live Traffic Updates';
        $data['page_name']  =   'admin/live-updates/edit-route-update';
        $data['route']      =   $this->Admin_model->get_route('routes');
        $data['route_status']=   $this->Admin_model->get_route_status('route_status');
		$data['route_record']=   $this->Admin_model->get_route_by_id($id);
        $data['action']	    =	base_url('admin/edit_route_status');
        //echo '<pre>';print_r($data['data']);
        $this->load->view('template', $data);
    }
    
    public function edit_route_status()
    {
       $id = $this->input->post('route_update_id');
         
        $data = array(
            //'route_id'         => $this->input->post('route'),
            'route_status_id'  => $this->input->post('status'),
            'updated_time'     => $this->input->post('time')
        );
		
		$this->Admin_model->update_route($id,$data);
		$this->session->set_flashdata('msg','Record has been Updated Successfully!!');
		redirect('admin/routes_list');
    }
    
}