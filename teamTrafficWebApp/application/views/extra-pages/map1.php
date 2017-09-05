
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
                    var vid=[];
                    var img=[];

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
                                createMarker(new google.maps.LatLng(latitude[i],longitude[i]),complaint_type[i],complaint_status[i],description[i],vid[i],img[i]);
                            }
//console.log(img);
                        }
                
                        var geocoder = new google.maps.Geocoder();
                        var latLng = new google.maps.LatLng(latitude[0],longitude[0]);
                        var address=[];
                    //console.log(latLng);
                        geocoder.geocode({      
                            latLng: latLng
                            }, 
                            function(responses) 
                            {
                                //window.alert(responses);
                               if (responses && responses.length > 0) 
                               {        
                                   for(var i=0; i < x; i++){
                                    var address = responses[0].formatted_address;
                                    //alert(responses[0].formatted_address);
                                    //alert(address);
                                   }
                               } 
                               else 
                               {       
                                 alert('Not getting Any address for given latitude and longitude.');
                               }   
                            }
                    );

                        var onMarkerClick = function() {
                          var marker = this;
                          var latLng = marker.getPosition();
                          var description = marker.content;
                          infowindow.setContent('<p><b>Description: </b>'+description+'</p>');
                          infowindow.open(map, marker);
                            
                                /*var geocoder  = new google.maps.Geocoder();             // create a geocoder object
                                var location  = new google.maps.LatLng(position.coords.latitude[0], position.coords.longitude[0]);    // turn coordinates into an object          
                                geocoder.geocode({'latLng': location}, function (results, status) {
                                if(status == google.maps.GeocoderStatus.OK) {           // if geocode success
                                var add=results[0].formatted_address;         // if address found, pass to processing function
                                document.write(add);
                                }
                                });*/
                        };

                    function createMarker(address,complaint_type,complaint_status,description,vid,img){
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
                            position: address,
                            icon: icon,
                            animation: google.maps.Animation.DROP,
                            content: '<p>'+description+'</p><p><b>Status is: </b>'+complaint_status+'</p><p><b>Complaint Type is: </b>'+complaint_type+'</p><p><b>Location is: </b>'+address+'</p><p><b>Complaint Image: </b><img src="http://localhost/kp-traffic-police/uploads/images/'+img+'" width="150px" height="150px" style="vertical-align:top;"/></p><p><b>Video: </b><video width="180" height="150" controls autoplay><source src="http://localhost/kp-traffic-police/uploads/videos/'+vid+'" type="video/mp4"></video></p>',
                            
                            map: map

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
