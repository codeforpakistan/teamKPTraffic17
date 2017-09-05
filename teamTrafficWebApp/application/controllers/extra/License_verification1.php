<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class License_verification extends CI_Controller
{
    
    public function __construct()
    {
        parent::__construct();
    }
    
    public function get_license_data()
    {
        header('Content-Type: text/html; charset=utf-8');
        
        if(isset($_POST['cnic'])){
            $cnic = $this->input->post('cnic');
            $lic_number = $this->input->post('dl_number');

            $this->db->where('cnic',$cnic);
            $this->db->where('lic_status',1);
            $query=$this->db->get('license_verification');
            $lic=$query->row();
            
            if(date('Y-m-d') <= $lic->license_expiry_date)
            {
                $result = $this->Complaintsmodel->licenseDB($cnic, $lic_number);
                //print_r($result); die;

                $Response = array('message' => 'License Verified!', 'status' => true, 'data' => $result);
                echo json_encode($Response);
            }elseif($query->row <= 0)
            {
                //update status to '0' in database
                //$update = $this->Complaintsmodel->updateLicStatus($cnic);
                
                $Response = array('message' => 'Invalid CNIC.', 'status' => false);
                echo json_encode($Response);
            }
            
            else
            {
                //update status to '0' in database
                $update = $this->Complaintsmodel->updateLicStatus($cnic);
                
                $Response = array('message' => 'License Expired! Renewal Required.', 'status' => false);
                echo json_encode($Response);
            }
        }
        else{
            echo 'Please Enter CNIC to Search record';
        }
        /*
        if(!empty($result))
        {
            $Response = array('message' => 'License Verified!', 'status' => true, 'data' => $result);
            echo json_encode($Response);
        }
        else{
            $Response = array('message' => 'License Expired! Renewal Required.', 'status' => false);
            echo json_encode($Response);
        }
        */
    }
    
}

?>