<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
 
class Excel extends CI_Controller {
    
    public function __construct(){
        parent::__construct();
        error_reporting(E_ALL);
        $this->load->library('PHPExcel');
        $this->load->library('PHPExcel/IOFactory');
    }
    
    public function index()
    {
        $this->load->view('excel');
    }
    
    public function upload(){
        $fileName = date('d_m_y').$_FILES['file']['name'];
         
        $config['upload_path'] = FCPATH.'uploads/excel';
        $config['file_name'] = $fileName;
        $config['allowed_types'] = 'xls|xlsx|csv';
        $config['max_size'] = 10000;
         
        $this->load->library('upload');
        $this->upload->initialize($config);
        //print_r($config); die;
        if(! $this->upload->do_upload('file') ){
            $this->session->set_flashdata('msg',$this->upload->display_errors());
            redirect('admin/add_license');
        }        
             
        $media = $this->upload->data();
        $inputFileName = $media['full_path'];
         
        
        try {
                $inputFileType = IOFactory::identify($inputFileName);
                $objReader = IOFactory::createReader($inputFileType);
                $objPHPExcel = $objReader->load($inputFileName);
            } catch(Exception $e) {
                die('Error loading file "'.pathinfo($inputFileName,PATHINFO_BASENAME).'": '.$e->getMessage());
            }
 
        
            // insert sheet into database
            $sheet = $objPHPExcel->getSheet(0);
            $highestRow = $sheet->getHighestRow();
            $highestColumn = $sheet->getHighestColumn();
             
            for ($row = 2; $row <= $highestRow; $row++){                  //  Read a row of data into an array                 
                $rowData = $sheet->rangeToArray('A' . $row . ':' . $highestColumn . $row,
                                                NULL,
                                                TRUE,
                                                FALSE);
                $liscenceno = $rowData[0][0];                
                $liscencetype = $rowData[0][4];

                $Checkif = $this->CheckValue('license_verification',$liscenceno,$liscencetype);
                if(empty($Checkif))
                {
                    //Insert data from excel to database                                
                     $data = array(
                        "dl_number"=> $rowData[0][0],
                        "cnic"=> $rowData[0][1],
                        "name"=> $rowData[0][2],
                        "father_name"=> $rowData[0][3],
                        "license_type"=> $rowData[0][4],
                        "license_expiry_date"=> date('Y-m-d', PHPExcel_Shared_Date :: ExcelToPHP ($rowData[0][5])),
                        "lic_status"=> $rowData[0][6],
                        "district_id"=> $rowData[0][7],
                    );
                    // print_r($data); die;
                    $insert = $this->db->insert("license_verification",$data);
                    $this->session->set_flashdata('msg','Your Sheet Has Been Uploaded Successfully to Database!');
                    //delete_files($media['file_path']);
                }
            }
        redirect('admin/add_license');
    }

    // check for duplicate rows
    function CheckValue($table,$no,$type)
    {
        $this->db->where('dl_number',$no);        
        $this->db->where('license_type',$type);
        $Query = $this->db->get($table);
        if($Query)
        {
           return $Query->result();
        }
    }
    
    function removeDuplicates($inputFileName, $objPHPExcel) {
            $worksheet = $objPHPExcel->getActiveSheet();
            $urn = array();
            foreach ($worksheet->getRowIterator() as $row) {
                $rowIndex = $row->getRowIndex();
                $cellValue = $worksheet->getCell('E'.$rowIndex)->getValue();
                array_push($urn, $cellValue);       
            }
            $numberOfURNs = count($urn);
            for ($rowIndex = $numberOfURNs; $rowIndex != 1; $rowIndex--) {
                $cellValue = $worksheet->getCell('E'.$rowIndex)->getValue();
                for ($i = $rowIndex - 2; $i != 0; $i--) {
                    if ($urn[$i] == $cellValue) {
                        $worksheet->removeRow($rowIndex);
                        $objWriter = IOFactory::createWriter($objPHPExcel, $inputFileType);
                        $objWriter->save($inputFileName);
                        break;  
                    }
                }
            }
            return $objPHPExcel = checkExtension($inputFileName);
    }
    
}