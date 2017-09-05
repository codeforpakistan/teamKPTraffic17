<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Challan extends CI_Controller
{
    
    public function __construct()
    {
        parent::__construct();
    }
    
    public function get_challan_info()
    {
        //$ticket_id = $this->input->post('ticket_id');
        
        //$result = $this->Complaintsmodel->challanDB($ticket_id);
        //print_r($result); die;
        
        $id = $_GET['TicketId'];
       
        include("includes/simple_html_dom.php");
        $html = new simple_html_dom();
        $base = $html->load('https://ttmspublic.a2z.care/public/SearchTicket.aspx?TicketId='.$id);
// echo $base; die;
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, FALSE);
        curl_setopt($curl, CURLOPT_HEADER, false);
        curl_setopt($curl, CURLOPT_FOLLOWLOCATION, true);
        curl_setopt($curl, CURLOPT_URL, $base);
        curl_setopt($curl, CURLOPT_REFERER, $base);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, TRUE);
        $str = curl_exec($curl);
        curl_close($curl);

        $html->load($str);

        $html->preserveWhiteSpace = false; 

        $theData = array();

        // loop over rows
        foreach($html->find('table[@id="ContentPlaceHolder1_DetailsView1"]') as $row) {
            // initialize array to store the cell data from each row
            $rowData = array();
            foreach($row->find('tr') as $cell) {
                // push the cell's text to the array
                $rowData[] = $cell->plaintext;
            }
            // push the row's data array to the 'big' array
            $theData[] = $rowData;
        }
        $html->clear(); 
        unset($html);
        /**
         * Removing Spaces & Getting Required Data From Array
         */
        $arrayOfData = $theData[0];
        $getDate 	 = substr(trim($theData[0][0]),4);
        $getDistrict = substr(trim($theData[0][1]),8);
        $getBname 	 = substr(trim($theData[0][2]),6);
        $dutyPoint 	 = substr(trim($theData[0][3]),9);
        $ticketId	 = substr(trim($theData[0][4]),8);
        $amount		 = substr(trim($theData[0][5]),6);
        $status 	 = substr(trim($theData[0][6]),6);

        /**
         * Simple For Printing
         */
        $array  = array(
         $getDate 	 = substr(trim($theData[0][0]),4).'<br>',
         $getDistrict = substr(trim($theData[0][1]),8).'<br>',
         $getBname 	 = substr(trim($theData[0][2]),6).'<br>',
         $dutyPoint 	 = substr(trim($theData[0][3]),9).'<br>',
         $ticketId	 = substr(trim($theData[0][4]),8).'<br>',
         $amount		 = substr(trim($theData[0][5]),6).'<br>',
         $status 	 = substr(trim($theData[0][6]),6).'<br>'
        );
        $result = $array;
        // print_r($result);

        /* Json result */
        if(!empty($result))
        {
            $Response = array('message' => 'Ticket Paid!', 'status' => true, 'data' => $result);
            echo json_encode($Response);
        }
        else{
            $Response = array('message' => 'Ticket is not Paid.', 'status' => false);
            echo json_encode($Response);
        }
        
    }
    
}

?>


<!--http://www.phpknowhow.com/basics/if-else-and-switch-case/-->