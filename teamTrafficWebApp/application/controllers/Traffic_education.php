<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Traffic_education extends CI_Controller
{
    
    public function __construct()
    {
        parent::__construct();
    }
    
    public function getEducationSigns()
    {
        header('Content-Type: text/html; charset=utf-8');
        
        $result = $this->Complaintsmodel->getEducationDB();
        
        //echo "<pre>";
        //print_r($result);
        
        if(!empty($result))
        {
            $Response = array('message' => 'Success!', 'status' => true, 'data' => $result);
            echo json_encode($Response); //, JSON_UNESCAPED_UNICODE
            //print_r(json_decode(json_encode($Response)));
        }
        else{
            $Response = array('message' => 'Failed', 'status' => false);
            echo json_encode($Response);
        }
    }
}

?>