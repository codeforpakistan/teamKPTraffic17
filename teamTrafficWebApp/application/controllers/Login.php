<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Login extends CI_Controller {
    
    public function __construct()
    {
        parent::__construct();

    }
    
    public function userLogin()
    {
        $cnic   = $this->input->post('cnic');
        // $password   = $this->input->post('password');
    
       $result = $this->Complaintsmodel->login($cnic);
        //print_r($result); die;
        if(!empty($result))
        {
            $Response = array('message' => 'Login Successfully!', 'status' => 'true', 'data'=> $result);
            echo json_encode($Response);
        }
        else{
            $Response = array('message' => 'Invalid name or password', 'status' => 'false');
            echo json_encode($Response);
        }
    }
    
}