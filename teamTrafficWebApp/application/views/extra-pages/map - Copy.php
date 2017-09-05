
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?php echo $heading; ?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Heat Map</li>
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
              <h3 class="box-title">Complaints Heat MAP</h3>
            </div>
            <!-- /.box-header -->
                           

            <div class="box-body">
             <script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyCe0I76FCBsgJP2dh193EWuX2IPST4gn0k&sensor=false"></script>
          
            <script type="text/javascript">
                    var latitude= [];
                    var longitude= [];
                    var complaint_type=[];
                    var complaint_status=[];
                    var description=[];
                    var adress=[];
                    var vid=[];
                    var img=[];
                var imgOrVid;
                //var addr=[];

                    var x=0;    
                    var map = null; 
                    var markerArray = []; 
                    var infowindow; 

                    <?php foreach($latlong as $row): ?>
                //print_r($latlong); die;
                
                        latitude.push(<?php echo $row['latitude']; ?>);
                        longitude.push(<?php echo $row['longitude']; ?>);
                        complaint_type.push("<?php echo $row['complaint_type']; ?>");
                        complaint_status.push("<?php echo $row['status']; ?>");
                        description.push("<?php echo $row['description']; ?>");
                        vid.push("<?php echo $row['video'];?>") ;
                        img.push("<?php echo $row['image']; ?>");
                    <?php 
               // print_r($row['video']);
                endforeach; ?>
                
            
                        x = <?php echo count($latlong); ?>;

                        function initialize() {
                            var myOptions = {

                                zoom: 12,
                                center: new google.maps.LatLng(latitude[0], longitude[0]),
                                mapTypeControl: true,
                                mapTypeControlOptions: {
                                    style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
                                },
                                navigationControl: true,
                                mapTypeId: google.maps.MapTypeId.ROADMAP
                            }
                            map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

                            infowindow = new google.maps.InfoWindow({
                                size: new google.maps.Size(150, 50)
                            });

                            google.maps.event.addListener(map, 'click', function() {
                                infowindow.close();
                            });

                            for (var i = 0; i < x; i++) {
                                // convert latLng to Address
                             /*   var geocoder = new google.maps.Geocoder();
                                var latLng = new google.maps.LatLng(latitude[i],longitude[i]);
                            //console.log(latLng);
                                geocoder.geocode({      
                                    latLng: latLng
                                    }, 
                                     function(responses) 
                                        {
                                            //window.alert(responses);
                                           if (responses && responses.length > 0) 
                                           {        
                                                addr = responses[0].formatted_address;
                                               adress.push(addr);
                                                //alert(address);
                                           } 
                                           else 
                                           {       
                                             alert('Not getting Any address for given latitude and longitude.');
                                           }
                                        }
                                );*/
                                
                                createMarker(new google.maps.LatLng(latitude[i],longitude[i]),complaint_type[i],complaint_status[i],description[i],vid[i],img[i],adress[i]);
                            }
                        }
                
                        var onMarkerClick = function() {
                          var marker = this;
                          var latLng = marker.getPosition();
                          var description = marker.content;
                         // var imgStr;
                            /*if((img!="") || (img!= 'null')) {
                            var imgStr = img;
                               //imgOrVid = imgStr;
                            //console.log(imgStr);
                            infowindow.setContent('<p><b>Complaint Image: </b><img src="http://localhost/kp-traffic-police/uploads/images/'+imgStr+'" width="150px" height="150px" style="vertical-align:top;"/></p>');
                        }else if((vid!="") || (vid!= 'null')) {
                               //var VidStr = ;
                              //imgOrVid = VidStr;
                            
                            infowindow.setContent('<p><b>Video: </b><video width="180" height="150" controls autoplay><source src="http://localhost/kp-traffic-police/uploads/videos/'+vid+'" type="video/mp4"></video></p>');
                        }else {
                            alert('no media found');
                        }*/
                            
                            var geocoder = new google.maps.Geocoder();
                            var addr;
                            var latLng = new google.maps.LatLng(latLng.lat(), latLng.lng());
                           
                                geocoder.geocode({      
                                    latLng: latLng
                                    }, 
                                     function(responses) 
                                        {
                                           
                                           if (responses && responses.length > 0) 
                                           {        
                                                addr = responses[0].formatted_address;
                                                  infowindow.setContent('<p><b>Location: </b>'+addr+'</p><p><b>Complaint Description: </b>'+description+'</p>');
                                           } 
                                           else 
                                           {       
                                             //alert('Not getting Any address for given latitude and longitude.');
                                                //addr = responses[0].formatted_address;
                                                  infowindow.setContent('<p><b>Location: </b>No Address Found</p><p><b>Complaint Description: </b>'+description+'</p>');
                                           }
                                        }
                                );
                            
                       // console.log(imgStr);
                            
                          infowindow.open(map, marker);
                        };
                
                
                    function createMarker(latlng,complaint_type,complaint_status,description,vid,img,adress){
                       
                        if((img!="") || (img!= 'null')) {
                            var imgStr = '<p><b>Complaint Image: </b><img src="http://localhost/kp-traffic-police/uploads/images/'+img+'" width="150px" height="140px" style="vertical-align:top;"/></p>';
                            imgOrVid = imgStr;
                            //console.log(imgStr);
                            //infowindow.setContent();
                        } if((vid!="") || (vid!= 'null')) {
                               var vidStr = '<p><b style="vertical-align:top;">Video: </b><video width="180" height="150" controls autoplay><source src="http://localhost/kp-traffic-police/uploads/videos/'+vid+'" type="video/mp4"></video></p>';
                              imgOrVid = vidStr;
                            
                            //infowindow.setContent();
                        }else {
                            alert('no media found');
                        }
                        
//                    alert(img);
//                        alert(vid);
                       //console.log(JSON.stringify(latlng));
                        var icon = "";
                        switch (complaint_status) {
                            case "In Progress":
                                icon = "yellow";
                                break;                      
                            case "Completed":
                                icon = "green";   
                                break;
                            case "Pending":
                                icon = "red";
                                break;
                                
                            
                        }
                        icon = "http://maps.google.com/mapfiles/ms/icons/"+icon+".png";
                        
                       var marker = new google.maps.Marker({
                            position: latlng,
                            icon: icon,
                            animation: google.maps.Animation.DROP,
                            content: '<p>'+description+'</p><p><b>Status: </b>'+complaint_status+'</p><p><b>Complaint Type: </b>'+complaint_type+'</p>'+(vid=='' ? ('<p><b>Complaint Image: </b><img src="http://localhost/kp-traffic-police/uploads/images/'+img+'" width="150px" height="140px" style="vertical-align:top;"/></p>') : ('<p><b style="vertical-align:top;">Video: </b><video width="180" height="150" controls autoplay><source src="http://localhost/kp-traffic-police/uploads/videos/'+vid+'" type="video/mp4"></video></p>')),
                            
                            map: map,
                            title: 'Click for mor details'
                        });
                        google.maps.event.addListener(marker, 'click', onMarkerClick);
                        markerArray.push(marker);
                    }

                    window.onload = initialize;


                </script>
              
              <div id="map_canvas"></div>
              <style type="text/css">
                #map_canvas {
                width: 100%;
                height: 700px;
                }
                  html, body {
                    height: 100%;
                    margin: 0;
                      padding: 0;}
              </style>
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
