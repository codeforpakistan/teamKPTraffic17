<?php

class Complaintsmodel extends CI_Model{
    
    /**
    *   Complaint Module API's
    **/
    public function InsertDB($data)
    {
        return $this->db->insert('complaints', $data);
        //echo $this->db->last_query(); die;
    }
    
    function my_complaints($table,$id, $search = "by_user_id")
    {
        $this->db
            ->select("complaint_id, name, cnic, latitude, longitude, description, image, video,dated, complaint_type, status")
            ->from($table)
            ->join('complaint_types','complaint_types.complaint_type_id = complaints.complaint_type_id', 'inner')
            ->join('complaints_status','complaints_status.complaints_status_id = complaints.complaints_status_id', 'inner')
            ->join('signup', 'signup.signup_id = complaints.signup_id', 'inner');
        if ($search == "by_complaint_id") {
            $this->db->where('complaint_id', $id);
        } else {
            $this->db->where('user_id', $id);
        }
        $this->db->order_by('complaint_id','DESC');
        
        $my_complaints	=	$this->db->get();
        //echo $this->db->last_query($my_complaints); die;

        if($my_complaints->num_rows() > 0){
            return $my_complaints->result();
        }
        else{
            return array();
        }
    }
    
    function completed_complaints($table,$id)
    {
        $this->db
            ->select("complaint_id, name, cnic, latitude, longitude, description, image, video,dated, complaint_type, status")
            ->from($table)
            ->join('complaint_types','complaint_types.complaint_type_id = complaints.complaint_type_id', 'inner')
            ->join('complaints_status','complaints_status.complaints_status_id = complaints.complaints_status_id', 'inner')
            ->join('signup', 'signup.signup_id = complaints.signup_id', 'inner');
        $this->db->where('user_id', $id);
        $this->db->where('complaints.complaints_status_id', 1);
        $this->db->order_by('complaint_id','DESC');
        
        $my_complaints	=	$this->db->get();
        //echo $this->db->last_query($my_complaints); die;

        if($my_complaints->num_rows() > 0){
            return $my_complaints->result();
        }
        else{
            return array();
        }
    }
    
    function pending_complaints($table,$id)
    {
        $this->db
            ->select("complaint_id, name, cnic, latitude, longitude, description, image, video,dated, complaint_type, status")
            ->from($table)
            ->join('complaint_types','complaint_types.complaint_type_id = complaints.complaint_type_id', 'inner')
            ->join('complaints_status','complaints_status.complaints_status_id = complaints.complaints_status_id', 'inner')
            ->join('signup', 'signup.signup_id = complaints.signup_id', 'inner');
        $this->db->where('user_id', $id);
        $this->db->where('complaints.complaints_status_id', 2);
        $this->db->order_by('complaint_id','DESC');
        
        $my_complaints	=	$this->db->get();
        //echo $this->db->last_query($my_complaints); die;

        if($my_complaints->num_rows() > 0){
            return $my_complaints->result();
        }
        else{
            return array();
        }
    }
    
    function inprogress_complaints($table,$id)
    {
        $this->db
            ->select("complaint_id, name, cnic, latitude, longitude, description, image, video,dated, complaint_type, status")
            ->from($table)
            ->join('complaint_types','complaint_types.complaint_type_id = complaints.complaint_type_id', 'inner')
            ->join('complaints_status','complaints_status.complaints_status_id = complaints.complaints_status_id', 'inner')
            ->join('signup', 'signup.signup_id = complaints.signup_id', 'inner');
        $this->db->where('user_id', $id);
        $this->db->where('complaints.complaints_status_id', 3);
        $this->db->order_by('complaint_id','DESC');
        
        $my_complaints	=	$this->db->get();
        //echo $this->db->last_query($my_complaints); die;

        if($my_complaints->num_rows() > 0){
            return $my_complaints->result();
        }
        else{
            return array();
        }
    }
    
    /**
    *    SignIn/LOgin Module API
    **/
    function login($cnic)
    {
        $this -> db -> select('signup_id, cnic, user_id');
        $this -> db -> from('signup');
        $this -> db -> where('cnic', $cnic);
        //$this -> db -> where('password', md5($password));

        $query = $this -> db -> get();
        //echo $this->db->last_query(); die;

        if($query -> num_rows() == 1)
        {
            return $query->result();
        }
        else
        {
            return false;
        }
    }
    
    // UPLOAD IMAGE FUNCTION
       function upload($image_name){
    
            $config['upload_path'] = './uploads/images/';
        
            $config['allowed_types'] = 'gif|jpg|png|jpeg';
            $config['max_size'] = '100000';
            $config['max_width']  = '10240';
            $config['max_height']  = '7680';
            $config['encrypt_name'] = true;
        
        $this->load->library('upload', $config);

        if ( !$this->upload->do_upload($image_name)){
        return false;
        }
        else{
        $data = array('upload_data' => $this->upload->data());
            return $data;
        }
    }
    
    
    /**
    *   SignUp/Registration Module
    **/
    public function userSignup($data)
    {
        $query = $this->db->query('SELECT (case when max(user_id) IS NULL then 1 else max(user_id)+1 end) as user_id FROM `signup`');
        $u_id = $query->row();
        
        $data = array(
            'name' => $data['name'],
            'email' => $data['email'],
            'cnic' => $data['cnic'],
            'phone_no' => $data['phone_no'],
            'user_id' => $u_id->user_id
        );

       //print_r($data); die;
        //$enc_password = $this->encrypt_password($data['password']);
        return $this->db->insert('signup',$data);
       
        /*$this->db->insert('signup',$data);
        $insertId = $this->db->insert_id();

        return  $insertId;*/
       // $query= $this->db->insert('signup',$data);
       // echo $query;
       // echo $this->db->last_query(); die;
    }
    
    
    /**
    *   Live Updates Module
    **/
    public function getUpdatedData($flag)
    {
        $this->db->select('route_name, route_status,updated_time');
        $this->db->from('route_updates');
        $this->db->join('routes','route_updates.route_id = routes.route_id', 'inner');
        $this->db->join('route_status','route_updates.route_status_id = route_status.route_status_id', 'inner');
        $this->db->where('flag', $flag);
        $this->db->order_by('route_update_id', 'desc');
        $this->db->limit(1);
        
        $query = $this->db->get();
        //echo $this->db->last_query(); die;
        if($query->num_rows() > 0)
        {
            return $query->result();
        }else{
            return false;
        }
    }
    
    
    /**
    *   License Verification
    **/
    public function licenseDB($cnic='',$lic_number='')
    {
        //$array = array('cnic' => $cnic, 'dl_number' => $lic_number);
        $where = "`cnic`=$cnic";
        
        $this->db->select('
            license_id,
            dl_number,
            cnic,
            license_verification.name as name,
            father_name,
            license_type,
            license_expiry_date,
            lic_status,
            license_verification.district_id,
            license_districts.name as district
        ');
        $this->db->from('license_verification');
        $this->db->join('license_districts', 'license_districts.name = license_verification.district_id', 'inner');
        //$this->db->or_where($array);
        $this->db->where($where);
        
        $query = $this->db->get();
       // echo $this->db->last_query(); die;
        
        if($query->num_rows() > 0)
        {
            return $query->result_array();
        }else{
            return false;
        }
    }
    
    public function updateLicStatus($cnic)
    {
        $data = array(
            'lic_status' => 0
        );
        
        $this->db->where('cnic',$cnic)
                ->update('license_verification', $data);
    }
    
    
    /**
    *   Challan Module
    **/
    public function challanDB($ticket_id='')
    {
        $this->db->select('*');
        $this->db->from('challan');
        $this->db->where('ticket_id', $ticket_id);
        
        $query = $this->db->get();
        //echo $this->db->last_query(); die;
        
        if($query->num_rows() > 0)
        {
            return $query->result_array();
        }else{
            return false;
        }
    }
    
    
    /***
    *   Traffic Education Module
    ***/
    public function getEducationDB()
    {
        //$unicode = mysql_query("SET NAMES 'utf8'");
        //mysql_query ("set character_set_results='utf8'");
        $this->db->select('*');
        $this->db->from('traffic_education');
        $this->db->order_by('traffic_education_id','DESC');
        
        $query = $this->db->get();
        //echo $this->db->last_query(); die;
        
        if($query->num_rows() > 0)
        {
            return $query->result();
        }else{
            return false;
        }
    }
    
    /***
    *   Emergency Contact
    ***/
    public function getEmergencyDB($id='', $lat='',$lng='')
    {
        $this->db->select('( 6371 * ACOS( COS( RADIANS( '.$lat.' ) ) * COS( RADIANS(  `latitude` ) ) * COS( RADIANS(  `longitude` ) - RADIANS( '.$lng.' ) ) + SIN( RADIANS( '.$lat.' ) ) * SIN( RADIANS(  `latitude` ) ) ) ) AS distance, emergency_contacts_detail.emergency_category_id, latitude, longitude, name, contact_no, category_name, division_name, district_name')
                ->from('emergency_contacts_detail')
                ->join('emergency_districts' , 'emergency_districts.emergency_districts_id = emergency_contacts_detail.emergency_district_id', 'INNER')
                ->join('emergency_divisions' , 'emergency_districts.emergency_division_id = emergency_divisions.emergency_division_id', 'INNER')
                ->join('emergency_categories' , 'emergency_contacts_detail.emergency_category_id = emergency_categories.emergency_category_id', 'INNER')
                ->where('emergency_contacts_detail.emergency_category_id',$id)
                ->HAVING('distance <= 5')
                ->ORDER_BY('distance', 'DESC');
        
        $query = $this->db->get();
        //echo $this->db->last_query(); die;
        
        if($query->num_rows() > 0)
        {
            return $query->result();
        }else{
            return false;
        }
    }
    
    public function getEmergencyCategoryListDB($id='', $lat=0, $lng=0)
    {
        $this->db->select('emergency_contacts_detail.emergency_category_id, name, contact_no, category_name, division_name, district_name')
                ->from('emergency_contacts_detail')
                ->join('emergency_districts' , 'emergency_districts.emergency_districts_id =  emergency_contacts_detail.emergency_district_id', 'INNER') 
                ->join('emergency_divisions' , 'emergency_districts.emergency_division_id = emergency_divisions.emergency_division_id' , 'INNER')
                ->join('emergency_categories' , 'emergency_contacts_detail.emergency_category_id = emergency_categories.emergency_category_id', 'INNER') 
                ->WHERE('emergency_contacts_detail.emergency_category_id', $id)
                ->ORDER_BY('emergency_contacts_detail.emergency_detail_id', 'DESC');
        
        $query = $this->db->get();
        //echo $this->db->last_query(); die;
        
        if($query->num_rows() > 0)
        {
            return $query->result();
        }else{
            return false;
        }
    }
    
    /*public function getEmergencyDB($lat='',$lng='',$id='')
    {
        
        $res =  $this->db->query("SELECT ( 6371 * ACOS( COS( RADIANS( $lat ) ) * COS( RADIANS(  `latitude` ) ) * COS( RADIANS(  `longitude` ) - RADIANS( $lng ) ) + SIN( RADIANS( $lat ) ) * SIN( RADIANS(  `latitude` ) ) ) ) AS distance,  `address`
    FROM `emergency_contacts_detail`
    WHERE `emergency_category_id` = '$id' HAVING distance <=5 ORDER BY distance DESC LIMIT 0 , 5"
        );
//        echo $this->db->last_query();
      if($res){
          return $res->result();
      }
    }*/
    
}
?>