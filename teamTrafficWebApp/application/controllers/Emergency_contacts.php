<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Emergency_contacts extends CI_Controller
{
    
    public function __construct()
    {
        parent::__construct();
    }
    
    public function getEmergencyContact()
    {
        header('Content-Type: text/html; charset=utf-8');
        
        $id = $_GET['category_id'];
        $lat = $_GET['latitude'];
        $lng = $_GET['longitude'];
        
        if(isset($id, $lat, $lng) && $lat!=0 && $lng!=0)
        {
            $result = $this->Complaintsmodel->getEmergencyDB($id,$lat,$lng);
            $Response = array('message' => 'Nearest Emergency Contact List!', 'status' => true, 'data' => $result);
            echo json_encode($Response);
        }elseif(!empty($id) && $lat==0 && $lng==0)
            {
                $id;
                $lat=00.000000;
                $lng=00.000000;
                
                $res = $this->Complaintsmodel->getEmergencyCategoryListDB($id,$lat=0,$lng=0);
                //print_r($result); die;
                $Response = array('message' => 'List of Emergency Category wise', 'status' => true, 'data' => $res);
                echo json_encode($Response);
            }
            else{
                $Response = array('message' => 'Sorry! No list found.', 'status' => false);
                echo json_encode($Response);
            }
        
    }
}

?>