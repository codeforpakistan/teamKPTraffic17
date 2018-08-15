<?php
defined('BASEPATH') OR exit('No direct script access allowed');
/*

require APPPATH . '/libraries/REST_Controller.php';
*/

class Signup extends CI_Controller {
   
    public function __construct() {
        parent::__construct();
        date_default_timezone_set("Asia/Karachi");
    }
    
    public function signup()
    {
        header('Content-Type: text/html');
        //echo current_url();
        
        $cnic = $this->input->post('cnic');
        /** checks cnic already exists **/
        $cnic_exists = $this->Complaintsmodel->login($cnic);
        
        if(!empty($cnic_exists))
        {
            $Response = array('message' => 'CNIC already exists! Try another.', 'status' => 'false');
            echo json_encode($Response);
        }else{
            
            $data = array(
                'name'          => $this->input->post('name'),
                'email'         => $this->input->post('email'),
                'cnic'          => $cnic,
                'phone_no'      => $this->input->post('phone_no')
            );
            
            // print_r($data); 
           // echo $password = $data['password'];
           // echo $enc_password = $this->encrypt_password($password);
            $result = $this->Complaintsmodel->userSignup($data);

            if(isset($result))
            {
                $Response = array('message' => 'Registration Successful, please login now!', 'status' => 'true', 'data'=>$result);
                echo json_encode($Response);
            }
            else{
                $Response = array('message' => 'Register Yourself First', 'status' => 'false');
                echo json_encode($Response);
            }
        }
    }
        
    //salt key + encrpyt password (sha1)
    public function encrypt_password($password)
    {
        $salt_key = '!@#$%&thisisaSALTkey*&^%%';
        $password = $password. $salt_key; //concatenate password and salt_key
        $enc_password = sha1($password);
        return $enc_password;
    }
}
