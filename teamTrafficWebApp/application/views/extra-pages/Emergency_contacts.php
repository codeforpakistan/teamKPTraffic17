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
        
        
        if(isset($_GET['category_id'], $_GET['latitude'], $_GET['longitude']) && $_GET['latitude']!=0 && $_GET['longitude']!=0)
        {
            $id = $_GET['category_id'];
            $lat = $_GET['latitude'];
            $lng = $_GET['longitude'];
            
            $result = $this->Complaintsmodel->getEmergencyDB($id,$lat,$lng);
            $Response = array('message' => 'Nearest Emergency Contact List!', 'status' => true, 'data' => $result);
            echo json_encode($Response);
        }else
            if(isset($_GET['category_id'], $_GET['latitude'], $_GET['longitude']))
            {
                $id = $_GET['category_id'];
                $lat = $_GET['latitude']=00.000000;
                $lng = $_GET['longitude']=00.000000;
                
                $res = $this->Complaintsmodel->getEmergencyCategoryListDB($id,$lat=0,$lng=0);
                //print_r($result); die;
                $Response = array('message' => 'List of Emergency Category wise', 'status' => true, 'data' => $res);
                echo json_encode($Response);
            }
            else{
                echo 'No Contact found';
            }
        
        
                
    /*  switch(true)
        {
            case $id!=0 && $lat!=0 && $lng!=0:
                echo 'Nearest Emergency Contact List!';
                break;
                
           case $id!=null && $lat=0 && $lng=0:
                echo 'List Category Wise!';
                break;
                
            case $id=null && $lat=null && $lng=null:
                echo 'No data found';
                break;
                
            default:
                echo 'No contact in list';
        }
        */
        
        
        /*if(isset($_GET['category_id'], $_GET['latitude'], $_GET['longitude']))
        {
            $id = $_GET['category_id'];
            $lat = $_GET['latitude'];
            $lng = $_GET['longitude'];
            $result = $this->Complaintsmodel->getEmergencyDB($id,$lat,$lng);
        
            //echo "<pre>";
            //print_r($result); die;

            if(!empty($result))
            {
                $Response = array('message' => 'Nearest Emergency Contact List!', 'status' => true, 'data' => $result);
                echo json_encode($Response);
            }
            else
                if(isset($_GET['category_id'], $_GET['latitude'], $_GET['longitude']))
                {
                    $id = $_GET['category_id'];
                    $lat = $_GET['latitude']=00.000000;
                    $lng = $_GET['longitude']=00.000000;
                    $result = $this->Complaintsmodel->getEmergencyCategoryListDB($id,$lat=0,$lng=0);
                //print_r($result); die;
                $Response = array('message' => 'List of Emergency Category wise', 'status' => true, 'data' => $result);
                echo json_encode($Response);
            }
        }else{
            echo 'No data found';
        }*/
    }
}

?>