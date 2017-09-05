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
        //header('Content-Type: text/html; charset=utf-8');
        header('Content-type: application/json');
        
        if(isset($_POST['cnic'])){
            $cnic = $this->input->post('cnic');
            $lic_number = $this->input->post('dl_number');

            $this->db->where('cnic',$cnic);
            //$this->db->where('lic_status',1);
            $query=$this->db->get('license_verification');
            //echo $this->db->last_query(); die;
            $lic=$query->row();
            
          // echo $lic->license_expiry_date; die;
            
            
            if(date('Y-m-d') <= $lic->license_expiry_date && $lic->lic_status==1)
            {
                $result = $this->Complaintsmodel->licenseDB($cnic);
                //print_r($result); die;

                $Response = array('message' => 'License Verified!', 'status' => true, 'data' => $result);
                echo json_encode($Response);
            }
            elseif((date('Y-m-d') > $lic->license_expiry_date) && ($lic->cnic==$cnic))
            {
                //update status to '0' in database
                $update = $this->Complaintsmodel->updateLicStatus($cnic);
                $Response = array('message' => 'License Expired! Renewal Required.', 'status' => true, 'data' => $update);
                echo json_encode($Response);
            }
            
            else 
            {
                //update status to '0' in database
               // $update = $this->Complaintsmodel->updateLicStatus($cnic);
                
                $Response = array('message' => 'Invalid CNIC.', 'status' => false);
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