<?php
// include google analyticsTracking
include("includes/analyticsTracking.php");

// function to get address by given latitude and longitude
function getAddress($lat, $lon){
    // Google map API (Freely Available)
 $url  = "http://maps.googleapis.com/maps/api/geocode/json?latlng=".$lat.",".$lon."&sensor=false";
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
?>
<?php
$i=1;
if(isset($complaint)) foreach($complaint as $row){
  //print_r($data);die;
  $latitude = $row->latitude;
  $longitude = $row->longitude;

  // Call Above function at top of page
  $address = getAddress($latitude, $longitude);
  ?>
  <div class="col-md-4">
     <?php if($row->image) {?>
     <a href="<?= base_url()."uploads/images/".$row->image;?>" data-lightbox="<?php echo $row->image;?>" data-title="<?php echo $row->image;?>">
      <img class="img-responsive" src="<?= base_url()."uploads/images/".$row->image;?>"/>
    </a>
    <?php } else if($row->video) { ?>
    <video width="100%" height="360" controls autoplay>
     <source src="<?= base_url()."uploads/videos/".$row->video; ?>" type="video/mp4">
       Sorry, your browser doesn't support the video element.
     </video>
     <?php }else { echo 'Sorry! No image/video to display'; }?>
  </div>
  <div class="col-md-8">
  <h3>Complaint</h3>
  <p><?= $row->description;?></p>
  <hr>
  <h3>Complaint Details</h3>
  <p><b>Name:</b> <?= $row->name;?></p>
  <p><b>Phone No:</b> <?= $row->phone_no;?></p>
  <p><b>Complaint Date:</b> <?= date('d-M-Y', strtotime($row->dated));?></p>
  <p><b>Complaint Type:</b> <?= $row->complaint_type;?></p>
  <p><b>Address:</b> 
    <?php 
      if(!empty($address)):
        echo $address;
      else:
        echo "Address Not found";
      endif;
    ?>
  </p>
  <p><b>Complaint Status:</b>
   <?php if($row->status == 'Completed'){ ?><span class="label label-success"><?php echo 'Completed'; ?></span> <?php }
   else if($row->status == 'Pending'){ ?><span class="label label-danger"><?php echo 'Pending'; ?></span><?php }
   else if($row->status == 'In Progress'){ ?><span class="label label-warning"><?php echo 'In Progress'; ?></span><?php }
   ?>
  </p>
  </div>
  
<?php
}else echo "No record Found!";    
?>