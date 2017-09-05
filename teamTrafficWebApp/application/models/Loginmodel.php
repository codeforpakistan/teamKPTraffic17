<?php

class Loginmodel extends CI_Model{

    function login($name, cnic)
    {
        $this -> db -> select('signup_id, cnic');
        $this -> db -> from('signup');
        $this -> db -> where('cnic', $cnic);
        //$this -> db -> where('password', md5($password));

        $query = $this -> db -> get();
        //echo $this->db->last_query(); die;

        if($query -> num_rows() == 1)
        {
            return $query->row();
        }
        else
        {
            return false;
        }
    }
    
}

    ?>