<?php class Admin_model extends CI_Model{
	
    /***
    ** LOGIN
    ***/
    public function validate_login($username , $password)
	{
		$query = $this->db->where(["admin_name"=>$username, "admin_password"=>$password])->get('admin_login');
		if($query->num_rows() > 0)   
		{
			return $query->row();
		}
		else
		{
			return NULL;
		}
	}
    
    /**
        * Dashboard main page (COUNTS)
    **/
    // COMPLAINTS MODULE COUNTS
    function get_all_complaints($table)
    {
        $this->db->select('count(*) as complaints')->from($table);
        $all_comp = $this->db->get();
        
        if($all_comp->num_rows() > 0)
        {
            return $all_comp->result();
        }else{
            return NULL;
        }
    }
    
    function completed_complaints($table)
    {
        $this->db->select('count(*) as completed')->from($table)->where('complaints_status_id',1);
        $completed = $this->db->get();
        
        if($completed->num_rows() > 0)
        {
            return $completed->result();
        }else{
            return NULL;
        }
    }
    
    function in_progress_complaints($table)
    {
        $this->db->select('count(*) as in_progress')->from($table)->where('complaints_status_id',3);
        $progress = $this->db->get();
        
        if($progress->num_rows() > 0)
        {
            return $progress->result();
        }else{
            return NULL;
        }
    }
    function pending_complaints($table)
    {
        $this->db->select('count(*) as pending')->from($table)->where('complaints_status_id',2);
        $pending = $this->db->get();
        
        if($pending->num_rows() > 0)
        {
            return $pending->result();
        }else{
            return NULL;
        }
    }
    
    /** 
        * Live Traffic Updates Module COUNTS
    **/
    function live_updates($table)
    {
        $this->db->select('count(*) as updates')->from($table);
        $updates = $this->db->get();
        
        if($updates->num_rows() > 0)
        {
            return $updates->result();
        }else{
            return NULL;
        }
    }
    
    function total_routes($table)
    {
        $this->db->select('count(*) as routes')->from($table);
        $routes = $this->db->get();
        
        if($routes->num_rows() > 0)
        {
            return $routes->result();
        }else{
            return NULL;
        }
    }
    
	/** 
        * Traffic Education Module COUNTS
    **/
    function traffic_signs($table)
    {
        $this->db->select('count(*) as signs')->from($table);
        $signs = $this->db->get();
        
        if($signs->num_rows() > 0)
        {
            return $signs->result();
        }else{
            return NULL;
        }
    }
    
    /** 
        * Emergency Contacts Module COUNTS
    **/
    function total_emergency_counts($table)
    {
        $this->db->select('count(*) as total')->from($table);
        $total = $this->db->get();
        
        if($total->num_rows() > 0)
        {
            return $total->result();
        }else{
            return NULL;
        }
    }
    function total_divisions($table)
    {
        $this->db->select('count(*) as divisions')->from($table);
        $divisions = $this->db->get();
        
        if($divisions->num_rows() > 0)
        {
            return $divisions->result();
        }else{
            return NULL;
        }
    }
    function total_districts($table)
    {
        $this->db->select('count(*) as districts')->from($table);
        $districts = $this->db->get();
        
        if($districts->num_rows() > 0)
        {
            return $districts->result();
        }else{
            return NULL;
        }
    }
    function total_rescue_records($table)
    {
        $this->db->select('count(*) as rescue')->from($table)->where('emergency_category_id',1);
        $rescue = $this->db->get();
        
        if($rescue->num_rows() > 0)
        {
            return $rescue->result();
        }else{
            return NULL;
        }
    }
    function total_health_records($table)
    {
        $this->db->select('count(*) as health')->from($table)->where('emergency_category_id',2);
        $health = $this->db->get();
        
        if($health->num_rows() > 0)
        {
            return $health->result();
        }else{
            return NULL;
        }
    }
    function total_mechanics_records($table)
    {
        $this->db->select('count(*) as mechanics')->from($table)->where('emergency_category_id',3);
        $mechanics = $this->db->get();
        
        if($mechanics->num_rows() > 0)
        {
            return $mechanics->result();
        }else{
            return NULL;
        }
    }
    function total_highway_records($table)
    {
        $this->db->select('count(*) as highway_officer')->from($table)->where('emergency_category_id',4);
        $highway_officer = $this->db->get();
        
        if($highway_officer->num_rows() > 0)
        {
            return $highway_officer->result();
        }else{
            return NULL;
        }
    }
    
    /** 
        * License Verification Module COUNTS
    **/
    function total_license_counts($table)
    {
        $this->db->select('count(*) as total_records')->from($table);
        $total_records = $this->db->get();
        
        if($total_records->num_rows() > 0)
        {
            return $total_records->result();
        }else{
            return NULL;
        }
    }
    public function verified_licenses($table)
    {
        $this->db->select('count(*) as verified')->from($table)->where('lic_status', 1);
        $verified = $this->db->get();
        
        if($verified->num_rows() > 0)
        {
            return $verified->result();
        }else{
            return NULL;
        }
    }
    public function expired_licenses($table)
    {
        $this->db->select('count(*) as expired')->from($table)->where('lic_status', 0);
        $expired = $this->db->get();
        
        if($expired->num_rows() > 0)
        {
            return $expired->result();
        }else{
            return NULL;
        }
    }
      
/********************************************** Dashboard COUNTS END ***************************************************/
    
    /*** 
    *   Traffic Education Module
    ***/
    function insert($table,$data)
    {
		$q= $this->db->insert($table,$data);
        //echo $this->db->last_query($q);
    }
    
	function select_all_signs($table){
        $this->db->select("*")->from($table);
        $this->db->order_by('traffic_education_id','DESC');
        $select_all_signs	=	$this->db->get();
        //echo $this->db->last_query($select_all_signs);

        if($select_all_signs->num_rows() > 0){
            return $select_all_signs->result();
        }
        else{
            return array();
        }
	}
    
    function get_record_by_id($id){
		 $this->db->select('
			image,
			image_title,
			image_description_eng,
			image_description_urdu
			')
			->from('traffic_education')
			->where('traffic_education_id',$id);
			$query = $this->db->get();	
		 	//echo $this->db->last_query();die;
			
			if($query->num_rows() > 0):
				return $query->row_array();
			else:
				return NULL;
			endif;
	 }
    
    // UPLOAD IMAGE FUNCTION
   function upload($image){
        //echo $path; die;
        $config['upload_path'] = './uploads/traffic-education';

        $config['allowed_types'] = 'gif|jpg|png|jpeg';
        $config['max_size'] = '100000';
        $config['max_width']  = '10240';
        $config['max_height']  = '7680';
        //$config['encrypt_name'] = true;

        $this->load->library('upload', $config);

        if ( !$this->upload->do_upload($image)){
        return false;
        }
        else{
        $data = array('upload' => $this->upload->data());
            return $data;
        }
        //print_r($data); die;
    }
	 
	 function edit($id,$data){
		 $this->db->where('traffic_education_id',$id)
		 ->update('traffic_education',$data);
		 //echo $this->db->last_query();die;
	 }
	
    function delete($table,$id){
		 $this->db->where('traffic_education_id',$id)
		      ->delete($table);
		// echo $this->db->last_query();die;
	 }
    
/****************************************** Traffic Education Module END ***********************************************/
    
    /**
    *   Complaints Module
    **/
    function get_complaints($table)
    {
        $this->db
            ->select("complaint_id, complaints.complaint_type_id, latitude, longitude, description, image, video, dated, complaint_type, status, signup.name, signup.phone_no")
            ->from($table)
            ->join('signup', 'signup.signup_id = complaints.signup_id', 'INNER ')
            ->join('complaint_types','complaint_types.complaint_type_id = complaints.complaint_type_id', 'inner')
            ->join('complaints_status','complaints_status.complaints_status_id = complaints.complaints_status_id', 'inner');
        $this->db->order_by('complaint_id','DESC');
        $select_complaints	=	$this->db->get();
        //echo $this->db->last_query($select_complaints);

        if($select_complaints->num_rows() > 0){
            return $select_complaints->result();
        }
        else{
            return array();
        }
    }
    
    function completed_complaints_list($table)
    {
        $this->db
            ->select("complaint_id, complaints.complaint_type_id, latitude, longitude, description, image, video,dated, complaint_type, status, signup.name, signup.phone_no")
            ->from($table)
            ->join('signup', 'signup.signup_id = complaints.signup_id', 'INNER ')
            ->join('complaint_types','complaint_types.complaint_type_id = complaints.complaint_type_id', 'inner')
            ->join('complaints_status','complaints_status.complaints_status_id = complaints.complaints_status_id', 'inner')
            ->where('complaints.complaints_status_id',1);
        $this->db->order_by('complaint_id','DESC');
        $select_complaints	=	$this->db->get();
        //echo $this->db->last_query($select_all_signs);

        if($select_complaints->num_rows() > 0){
            return $select_complaints->result();
        }
        else{
            return array();
        }
    }
    function inprogress_complaints_list($table)
    {
        $this->db
            ->select("complaint_id, complaints.complaint_type_id, latitude, longitude, description, image, video,dated, complaint_type, status, signup.name, signup.phone_no")
            ->from($table)
            ->join('signup', 'signup.signup_id = complaints.signup_id', 'INNER ')
            ->join('complaint_types','complaint_types.complaint_type_id = complaints.complaint_type_id', 'inner')
            ->join('complaints_status','complaints_status.complaints_status_id = complaints.complaints_status_id', 'inner')
            ->where('complaints.complaints_status_id',3);
        $this->db->order_by('complaint_id','DESC');
        $select_complaints	=	$this->db->get();
        //echo $this->db->last_query($select_all_signs);

        if($select_complaints->num_rows() > 0){
            return $select_complaints->result();
        }
        else{
            return array();
        }
    }
    function pending_complaints_list($table)
    {
        $this->db
            ->select("complaint_id, complaints.complaint_type_id, latitude, longitude, description, image, video,dated, complaint_type, status, signup.name, signup.phone_no")
            ->from($table)
            ->join('signup', 'signup.signup_id = complaints.signup_id', 'INNER ')
            ->join('complaint_types','complaint_types.complaint_type_id = complaints.complaint_type_id', 'inner')
            ->join('complaints_status','complaints_status.complaints_status_id = complaints.complaints_status_id', 'inner')
            ->where('complaints.complaints_status_id',2);
        $this->db->order_by('complaint_id','DESC');
        $select_complaints	=	$this->db->get();
        //echo $this->db->last_query($select_all_signs);

        if($select_complaints->num_rows() > 0){
            return $select_complaints->result();
        }
        else{
            return array();
        }
    }
    function delete_complaint($table,$id){
		 $this->db->where('complaint_id',$id)
		      ->delete($table);
		// echo $this->db->last_query();die;
	 }
    
    /**
    *   Complaints Update Functions
    **/
    function get_record($id){
		 $this->db->select('
			complaint_id, 
            complaint_type_id,
            complaints_status_id,
            latitude, 
            longitude, 
            description, 
            image, 
            video,
            dated
			')
			->from('complaints')
			->where('complaint_id',$id);
			$query = $this->db->get();	
		 	//echo $this->db->last_query();die;
			
			if($query->num_rows() > 0):
				return $query->row_array();
			else:
				return NULL;
			endif;
	 }
    function update_complaint($id,$data){
		 $this->db->where('complaint_id',$id)
		 ->update('complaints',$data);
		 //echo $this->db->last_query();die;
	 }
    
    function get_complaint_types()
    {
        $this->db->select('*')->from('complaint_types');
        $query = $this->db->get();
			
        if($query->num_rows() > 0):
            return $query->result();
        else:
            return NULL;
        endif;
    }
    function get_complaint_status()
    {
        $this->db->select('*')->from('complaints_status');
        $query = $this->db->get();
			
        if($query->num_rows() > 0):
            return $query->result();
        else:
            return NULL;
        endif;
    }
    
/******************************************** Complaints Module END ***************************************************/
    
    /***
    **  License Verification Module
    ***/
    public function select_all_records($table)
    {
        $this->db->select("
            license_id,
            dl_number,
            cnic,
            name,
            father_name,
            license_type,
            license_expiry_date,
            lic_status,
            district_id as district
        ")
        ->from($table);
        $this->db->limit(1000);
            //->join('license_districts', 'license_districts.district_id = license_verification.district_id', 'inner');
        $this->db->order_by('license_id','DESC');
        $select_all	=	$this->db->get();
//echo $this->db->last_query();die;
        if($select_all->num_rows() > 0){
            return $select_all->result();
        }
        else{
            return array();
        }
    }
    
    public function get_license_by_id($id)
    {
        $this->db->select('
            license_id,
			dl_number,
            cnic,
            license_verification.name,
            father_name,
            license_verification.license_type,
            license_expiry_date,
            lic_status,
            license_verification.district_id as district
			')
			->from('license_verification')
            ->join('license_districts', 'license_districts.name = license_verification.district_id', 'inner')
            ->join('license_types', 'license_types.license_type = license_verification.license_type', 'inner')
			->where('license_id',$id);
			$query = $this->db->get();	
		 	//echo $this->db->last_query();die;
			
			if($query->num_rows() > 0):
				return $query->row_array();
			else:
				return NULL;
			endif;
    }
    
    function update_license($id,$data){
		 $this->db->where('license_id',$id)
		          ->update('license_verification',$data);
    }
    
    // License VERIFICATION DISTRICT
    function districts_list($table)
    {
        $this->db->select("*")->from($table);
        $this->db->order_by('district_id','DESC');
        $select_all	=	$this->db->get();

        if($select_all->num_rows() > 0){
            return $select_all->result();
        }
        else{
            return array();
        }
    }
    
    function get_lic_districts($table)
    {
        $this->db->select('*')->from('license_districts');
        $query = $this->db->get();

        if($query->num_rows() > 0):
            return $query->result();
        else:
            return NULL;
        endif;
    }
    
    function lic_district_by_id($id)
    {
        $this->db->select('name')
			     ->from('license_districts')
			     ->where('district_id',$id);
        $query = $this->db->get();	
        //echo $this->db->last_query();die;

        if($query->num_rows() > 0):
            return $query->row_array();
        else:
            return NULL;
        endif;
    }
    function update_license_district($id,$data){
		 $this->db->where('district_id',$id)
		          ->update('license_districts',$data);
    }
    
    function delete_lic_district($table,$id){
		 $this->db->where('district_id',$id)
		      ->delete($table);
	 }
    
    // License VERIFICATION TYPES
    function types_list($table)
    {
        $this->db->select("*")->from($table);
        $this->db->order_by('type_id','DESC');
        $select_all	=	$this->db->get();

        if($select_all->num_rows() > 0){
            return $select_all->result();
        }
        else{
            return array();
        }
    }
    
    function lic_type_by_id($id)
    {
        $this->db->select('license_type')
			     ->from('license_types')
			     ->where('type_id',$id);
        $query = $this->db->get();	
        //echo $this->db->last_query();die;

        if($query->num_rows() > 0):
            return $query->row_array();
        else:
            return NULL;
        endif;
    }
    function update_license_type($id,$data){
		 $this->db->where('type_id',$id)
		          ->update('license_types',$data);
    }
    
    function delete_lic_type($table,$id){
		 $this->db->where('type_id',$id)
		      ->delete($table);
	 }
    
    function get_lic_types($table)
    {
        $this->db->select('*')->from('license_types');
        $query = $this->db->get();

        if($query->num_rows() > 0):
            return $query->result();
        else:
            return NULL;
        endif;
    }
    //check if license type already exists
    public function check_type($type)
    {
        $this->db->where('license_type',$type);
        $query=$this->db->get('license_types');
        if($query->num_rows()>0)
        {
            return true;
        }
    }
    //check if license district already exists
    public function check_dis($dis)
    {
        $this->db->where('name',$dis);
        $query=$this->db->get('license_districts');
        if($query->num_rows()>0)
        {
            return true;
        }
    }
  
/**************************************** License Verification Module END *************************************************/
    
/***
** Live Updates Module START 
***/
    
    function get_route($table)
    {
        $this->db->select('*')->from($table);
        $query = $this->db->get();

        if($query->num_rows() > 0):
            return $query->result();
        else:
            return NULL;
        endif;
    }
    
    function get_route_status($table)
    {
        $this->db->select('*')->from($table);
        $query = $this->db->get();

        if($query->num_rows() > 0):
            return $query->result();
        else:
            return NULL;
        endif;
    }
    
    function select_all_routes($table)
    {
        $this->db->select("route_update_id, route_name, route_status, updated_time")
                 ->from($table)
                 ->join('routes', 'route_updates.route_id= routes.route_id', 'inner')
                 ->join('route_status', 'route_updates.route_status_id = route_status.route_status_id', 'inner');
        $this->db->order_by('updated_time','DESC');
        $select_all	=	$this->db->get();

        if($select_all->num_rows() > 0){
            return $select_all->result();
        }
        else{
            return array();
        }
    }
    
    function get_route_by_id($id)
    {
        $this->db->select('route_update_id, routes.route_id, route_status.route_status_id, route_name, route_status, updated_time')
			->from('route_updates')
            ->join('routes', 'route_updates.route_id= routes.route_id', 'inner')
            ->join('route_status', 'route_updates.route_status_id = route_status.route_status_id', 'inner')
			->where('route_update_id',$id);
			$query = $this->db->get();	
		 	//echo $this->db->last_query();die;
			
			if($query->num_rows() > 0):
				return $query->row_array();
			else:
				return NULL;
			endif;
    }
    
    function update_route($id,$data){
		 $this->db->where('route_update_id',$id)
		          ->update('route_updates',$data);
    }
    
}
