<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Excel_import extends CI_Controller {
    
    public function __construct()
    {
        parent::__construct();
        error_reporting(E_ALL);
		//$this->load->model('excel_model');
    }
    
    public function index()
    {
        $this->load->view('excel_import');
    }
    
    public function import_data()
    {
        $config = array(
            'upload_path'   => FCPATH.'uploads/excel',
            'allowed_types' => 'xls|xlsx'
        );
        $this->load->library('upload', $config);
        if($this->upload->do_upload('file')){
            $data = $this->upload->data();
            //@chmod($data['full_path'], 0777);
            
            $this->load->library('Spreadsheet_Excel_Reader');
            $this->spreadsheet_excel_reader->setOutputEncoding('CP1251');
            
            $this->spreadsheet_excel_reader->read($data['full_path']);
            $sheets = $this->spreadsheet_excel_reader->sheets[0];
            error_reporting(0);
            
            $data_excel = array();
            for ($i = 2; $i <= $sheets['numRows']; $i++) {
                if($sheets['cells'][$i][1] == '') break;
                
                $data_excel[$i - 1]['dl_number'] = $sheets['cells'][$i][1];
                $data_excel[$i - 1]['cnic']       = $sheets['cells'][$i][2];
                $data_excel[$i - 1]['name']       = $sheets['cells'][$i][3];
                $data_excel[$i - 1]['father_name'] = $sheets['cells'][$i][4];
                $data_excel[$i - 1]['license_type'] = $sheets['cells'][$i][5];
                $data_excel[$i - 1]['license_expiry_date'] = $sheets['cells'][$i][6];
                $data_excel[$i - 1]['lic_status']  = $sheets['cells'][$i][7];
                $data_excel[$i - 1]['district_id'] = $sheets['cells'][$i][8];
              
            }
            echo '<pre>';
            print_r($data_excel); 
            echo '<pre>'; die;
            $this->db->insert_batch('license_verification', $data_excel);
            redirect('admin/add_license');
        }
    }
    
}
?>