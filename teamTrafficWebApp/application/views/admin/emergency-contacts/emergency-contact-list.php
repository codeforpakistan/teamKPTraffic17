<?php
 error_reporting(0);
//function to get address by given latitude and longitude
  function getAddress($lat, $lon){
    //Google map API (Freely Available)
       $url  = "http://maps.googleapis.com/maps/api/geocode/json?latlng=".
                $lat.",".$lon."&sensor=false";
    //Get content from google map api in json format
       $json = @file_get_contents($url);
    //decode data
       $data = json_decode($json);
       $status = $data->status;
       $address = '';
       if($status == "OK"){
          $address = $data->results[0]->formatted_address;
        }
       return $address;
      }

/*//you can change according to requirement or make it dynamic
$latitude = '28.448381';  
$longitude = '77.066707';
// Call Above function
$address = getAddress($latitude, $longitude);
   
if($address)
{
echo 'Address = '.$address;
}
else
{
echo "Address Not found";
}*/
// Output is 1868, Block A, Green Wood City, Sector 45, Gurgaon, Haryana 122003, India

?>
 
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?php echo $heading; ?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Emergency Contacts</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
    <!-- Main content dataTable -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Emergency Contact List</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                 <!-- session message -->
                <?php if($this->session->flashdata('msg')):?>
                <div class="callout callout-success" id="msg">
                    <p align="center" style="position:relative; font-size:16px;">
                        <?=$this->session->flashdata('msg')?>
                    </p>
                </div>
                <?php endif;?>
              <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                <tr>
                  <th>S.No</th>
                  <th>Emergency Category</th>
                  <th>Emergency Division</th>
                  <th>Emergency District</th>
                  <th>Address</th>
                  <!--<th>Longitude</th>-->
                  <th>Name</th>
                  <th>Contact No</th>
                  <th>Action</th>
                </tr>
                </thead>
                
                <tbody>
                <?php
                    $i=1;
                    if(isset($data)) foreach($data as $row){
                    //print_r($data);die;
                        $latitude = $row->latitude;
                        $longitude = $row->longitude;
                        
                         // Call Above function at top of page
                          $address = getAddress($latitude, $longitude);
                ?>
                <tr>
                  <td><?= $i;?></td>
                  <td><?=$row->category_name;?></td>
                  <td><?=$row->division_name;?></td>
                  <td><?=$row->district_name;?></td>
                  <td><?php if($address){
                            echo $address;
                        }else{
                             echo "Address Not found";
                  }?></td>
                  
                  <td><?= $row->name;?></td>
                  <td><?= $row->contact_no;?></td>
                  <td>
                      <a href="admin_emergency/edit_emergency_contact/<?=$row->emergency_detail_id?>" class="btn btn-primary"><i class="fa fa-pencil-square-o"></i></a>
                      <a onclick="return confirm('Are you sure you want to delete this?');" href="admin_emergency/delete_emergency_contact/<?=$row->emergency_detail_id?>" class="btn btn-danger"><i class="fa fa-trash-o"></i></a>
                  </td>
                </tr>
                <?php
                    $i++;
                    }else echo "No record Found!";    
                ?>
                </tbody>
                <tfoot>
                <tr>
                  <th>S.No</th>
                  <th>Emergency Category</th>
                  <th>Emergency Division</th>
                  <th>Emergency District</th>
                  <th>Address</th>
                  <!--<th>Longitude</th>-->
                  <th>Name</th>
                  <th>Contact No</th>
                  <th>Action</th>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->


    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
