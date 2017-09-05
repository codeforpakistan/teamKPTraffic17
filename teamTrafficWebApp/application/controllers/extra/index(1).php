<?php

    include("simple_html_dom.php");
    /*$html = file_get_html("https://ttmspublic.a2z.care/public/SearchTicket.aspx?TicketId=2022");
    $title = $html->find("table#ContentPlaceHolder1_DetailsView1", 0)->outertext;
echo $title;*/

$ticketID = $_POST['ticketid'];
//$html->load('<html><body><p>Hello World!</p><p>We\'re here</p></body></html>');include('simple_html_dom.php');
$html = new simple_html_dom();
$base = $html->load('https://ttmspublic.a2z.care/public/SearchTicket.aspx?TicketId='.$ticketID);

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
if (!empty($arrayOfData)):
    $jsonresponse = array(
        'message' => 'Give Title Here',
        'status'  => TRUE,
        'data'    => array(
                'date'         =>  substr(trim($theData[0][0]),4),
                'district'     =>  substr(trim($theData[0][1]),8),
                'name'         =>  substr(trim($theData[0][2]),6),
                'duty_point'   =>  substr(trim($theData[0][3]),9),
                'ticket_id'    =>  substr(trim($theData[0][4]),8),
                'amount'       =>  substr(trim($theData[0][5]),6),
                'status'       =>  substr(trim($theData[0][6]),6),
            ),
        );
else:
    $jsonresponse = array(
        'message' => 'Give Title Here',
        'status'  => FALSE,
        );
endif;

echo json_encode($jsonresponse);

?>