<?php class Emergency_model extends CI_Model{
	
    function __construct()
    {
        parent::__construct();
    }
	/** 
        * Dashboard Emergency Module Module 
    **/
    function insert($table,$data)
    {
		$q = $this->db->insert($table,$data);
        //echo $this->db->last_query($q); die;
    }
    
	function emergency_list(){
        $select = $this->db->query('SELECT emergency_detail_id, emergency_contacts_detail.emergency_category_id, latitude, longitude, name, contact_no, category_name, division_name, district_name
                        FROM emergency_contacts_detail
                        INNER JOIN emergency_districts ON emergency_districts.emergency_districts_id = emergency_contacts_detail.emergency_district_id 
                        INNER JOIN emergency_divisions ON emergency_districts.emergency_division_id = emergency_divisions.emergency_division_id 
                        INNER JOIN emergency_categories ON emergency_contacts_detail.emergency_category_id = emergency_categories.emergency_category_id
                        ORDER BY emergency_detail_id DESC');
        
        //echo $this->db->last_query($select);
        if($select->num_rows() > 0){
            return $select->result();
        }
        else{
            return NULL;
        }
	}
    
    function get_category($table)
    {
        $this->db->select('*')->from($table);
        $category =	$this->db->get();

        if($category->num_rows() > 0){
            return $category->result();
        }
        else{
            return NULL;
        }
    }
    function get_division($table)
    {
        $this->db->select('*')->from($table);
        $division =	$this->db->get();

        if($division->num_rows() > 0){
            return $division->result();
        }
        else{
            return NULL;
        }
    }
    
    function get_districts($division)
    {
        $this->db->select('emergency_districts_id,district_name');
 
         if($division != 0){
             $this->db->where('emergency_division_id', $division);
         
             $query = $this->db->get('emergency_districts');
             return $query->result();
         }
    }
    
    function get_record_by_id($id){
		 $this->db->select('
			emergency_category_id,
            emergency_district_id,
            emergency_division_id,
            district_name,
            latitude,
            longitude,
            name,
            contact_no
			')
			->from('emergency_contacts_detail')
            ->JOIN('emergency_districts', 'emergency_districts.emergency_districts_id = emergency_contacts_detail.emergency_district_id', 'inner')
			->where('emergency_detail_id',$id);
			$query = $this->db->get();	
		 	//echo $this->db->last_query();die;
			
			if($query->num_rows() > 0):
				return $query->row_array();
			else:
				return NULL;
			endif;
	 }
	 
	 function edit($id,$data){
		 $this->db->where('emergency_detail_id',$id)
		          ->update('emergency_contacts_detail',$data);
		 //echo $this->db->last_query();die;
	 }
	
    function delete($table,$id){
		 $this->db->where('emergency_detail_id',$id)
		          ->delete($table);
		// echo $this->db->last_query();die;
	 }
    
/*========================================================================================*/
    
    /**
    *   Division Queries
    **/
    function insert_division($table,$data)
    {
		$this->db->insert($table,$data);
        //echo $this->db->last_query($q);
    }
    
    function delete_division($table,$id){
		 $this->db->where('emergency_division_id',$id)
		      ->delete($table);
		// echo $this->db->last_query();die;
	 }
    
    function get_division_by_id($id){
		 $this->db->select('division_name')
			->from('emergency_divisions')
			->where('emergency_division_id',$id);
			$query = $this->db->get();	
		 	//echo $this->db->last_query();die;
			
			if($query->num_rows() > 0):
				return $query->row_array();
			else:
				return NULL;
			endif;
	 }
	 
	 function edit_division($id,$data){
		 $this->db->where('emergency_division_id',$id)
		 ->update('emergency_divisions',$data);
		 //echo $this->db->last_query();die;
	 }
    
    //check if division already exists
    public function check_division($div_name)
    {
        $this->db->where('division_name',$div_name);
        $query=$this->db->get('emergency_divisions');
        if($query->num_rows()>0)
        {
            return true;
        }
    }
    
/*=========================================================================================*/
    
    /**
    *   Districts Queries
    **/
    function insert_district($table,$data)
    {
		$this->db->insert($table,$data);
        //echo $this->db->last_query($q);
    }
    
    function districts_list($table)
    {
        $this->db->select('*')->from($table);
        $this->db->join('emergency_divisions', 'emergency_districts.emergency_division_id = emergency_divisions.emergency_division_id', 'INNER');
        $this->db->order_by('emergency_districts.emergency_division_id', 'DESC');
        $districts = $this->db->get();

        if($districts->num_rows() > 0){
            return $districts->result();
        }
        else{
            return NULL;
        }
    }
    
    function delete_district($table,$id){
		 $this->db->where('emergency_districts_id',$id)
		      ->delete($table);
		// echo $this->db->last_query();die;
	 }
    
    function get_district_by_id($id){
		 $this->db->select('emergency_division_id, district_name')
			->from('emergency_districts')
			->where('emergency_districts_id',$id);
			$query = $this->db->get();	
		 	//echo $this->db->last_query();die;
			
			if($query->num_rows() > 0):
				return $query->row_array();
			else:
				return NULL;
			endif;
	 }
	 
	 function edit_district($id,$data){
		 $this->db->where('emergency_districts_id',$id)
		 ->update('emergency_districts',$data);
		 //echo $this->db->last_query();die;
	 }
    
    //check if district already exists
    public function check_district($district_name)
    {
        $this->db->where('district_name',$district_name);
        $query=$this->db->get('emergency_districts');
        if($query->num_rows()>0)
        {
            return true;
        }
    }
}
