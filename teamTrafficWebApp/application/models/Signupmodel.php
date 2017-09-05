<?php

class Signupmodel extends CI_Model{

    public function get_user($id){
        $this->db->select('
		 	signup_id,
			name,
            email,
			cnic,
            phone_no
		 ')
            ->from('signup')
            ->where('signup_id', $id);
        $query = $this->db->get();

        if($query->num_rows() > 0):
        return $query->row_array();
        else:
        return NULL;
        endif;
    }
    
    public function insert_user($data)
    {
        //$enc_password = $this->encrypt_password($data['password']);
        return $this->db->insert('signup',$data);
        //echo $this->db->last_query();die;
    }
    
    /*public function encrypt_password($password)
    {
        $salt_key = '!@#$%&thisisaSALTkey*&^%%';
        $password = $password. $salt_key; //concatenate password and salt_key
        $enc_password = sha1($password);
        return $enc_password;
    }*/
    
}

    ?>