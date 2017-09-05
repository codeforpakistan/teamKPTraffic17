<?php
    // include google analyticsTracking
    include("includes/analyticsTracking.php");
?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?= $heading;?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
     <h4>Complaints</h4>
      <!-- Info boxes -->
      <div class="row">
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-purple"><i class="fa fa-globe"></i></span>

            <div class="info-box-content">
                <span class="info-box-text"><a href="admin/get_complaints">Total Complaints</a></span>
              <span class="info-box-number"><?php if(isset($all_complaints[0])){ echo $all_complaints[0]->complaints; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="fa fa-exclamation-circle"></i></span>

            <div class="info-box-content">
                <span class="info-box-text"><a href="admin/pending_complaints">Pending Complaints</a></span>
              <span class="info-box-number"><?php if(isset($pending[0])){ echo $pending[0]->pending;} else echo 0; ?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->

        <!-- fix for small devices only -->
        <div class="clearfix visible-sm-block"></div>

        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-yellow"><i class="fa fa-inbox"></i></span>

            <div class="info-box-content">
                <span class="info-box-text"><a href="admin/inprogress_complaints">In Progress Complaints</a></span>
              <span class="info-box-number"><?php if(isset($in_progress[0])){ echo $in_progress[0]->in_progress;} else echo 0; ?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-green"><i class="fa fa-check-square-o"></i></span>

            <div class="info-box-content">
                <span class="info-box-text"><a href="admin/completed_complaints">Completed Complaints</a></span>
              <span class="info-box-number"><?php if(isset($completed[0])){ echo $completed[0]->completed;} else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
        
    <!-- Live Traffic Updates content -->
     <h4>Live Traffic Updates</h4>
      <!-- Info boxes -->
      <div class="row">
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-navy"><i class="fa fa-road"></i></span>

            <div class="info-box-content">
                <span class="info-box-text"><a href="admin/routes_list">Total Live Traffic Updates</a></span>
              <span class="info-box-number"><?php if(isset($live_updates[0])){ echo $live_updates[0]->updates; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        
        <!--<div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-light-blue"><i class="fa fa-bus"></i></span>

            <div class="info-box-content">
                <span class="info-box-text">Total Routes</span>
              <span class="info-box-number"><?php //if(isset($total_routes[0])){ echo $total_routes[0]->routes; } else{ echo 0; }?></span>
            </div>
          </div>
        </div>-->
        </div>
        
     <!-- License Verification content -->
     <h4>License Verification</h4>
      <!-- Info boxes -->
      <div class="row">
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-teal"><i class="fa fa-id-card-o"></i></span>

            <div class="info-box-content">
                <span class="info-box-text"><a href="admin/get_license_list">Total License Records</a></span>
              <span class="info-box-number"><?php if(isset($license_records[0])){ echo $license_records[0]->total_records; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-green"><i class="fa fa-check"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Verified Licenses</span>
              <span class="info-box-number"><?php if(isset($verified_licenses[0])){ echo $verified_licenses[0]->verified; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="fa fa-close"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Expired Licenses</span>
              <span class="info-box-number"><?php if(isset($expired_licenses[0])){ echo $expired_licenses[0]->expired; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        </div>
       
     <!-- Traffic  Education content -->
     <h4>Traffic Education</h4>
      <!-- Info boxes -->
      <div class="row">
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-light-blue"><i class="fa fa-car"></i></span>

            <div class="info-box-content">
                <span class="info-box-text"><a href="admin/view_all_signs">Total Traffic Symbols</a></span>
              <span class="info-box-number"><?php if(isset($traffic_signs[0])){ echo $traffic_signs[0]->signs; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        </div>
        
     <!-- Emergency Contacts content -->
     <h4>Emergency Contacts</h4>
      <!-- Info boxes -->
      <div class="row">
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="fa fa-medkit"></i></span>

            <div class="info-box-content">
                <span class="info-box-text"><a href="admin_emergency/emergency_list">Total Contacts</a></span>
              <span class="info-box-number"><?php if(isset($total_contacts[0])){ echo $total_contacts[0]->total; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="fa fa-university"></i></span>

            <div class="info-box-content">
                <span class="info-box-text"><a href="admin_emergency/show_divisions">Total Divisions</a></span>
              <span class="info-box-number"><?php if(isset($total_divisions[0])){ echo $total_divisions[0]->divisions; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-navy"><i class="fa fa-street-view"></i></span>

            <div class="info-box-content">
                <span class="info-box-text"><a href="admin_emergency/show_districts">Total Districts</a></span>
              <span class="info-box-number"><?php if(isset($total_districts[0])){ echo $total_districts[0]->districts; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        </div>
        
        <!-- Info boxes Emergency Continuous -->
      <div class="row">
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-orange"><i class="fa fa-fire"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Rescue 1122</span>
              <span class="info-box-number"><?php if(isset($rescue_records[0])){ echo $rescue_records[0]->rescue; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="fa fa-heartbeat"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Medical Assistance</span>
              <span class="info-box-number"><?php if(isset($health_records[0])){ echo $health_records[0]->health; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-teal"><i class="fa fa-wrench"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Mechanics</span>
              <span class="info-box-number"><?php if(isset($mechanics_records[0])){ echo $mechanics_records[0]->mechanics; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="fa fa-road"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Highway Officer</span>
              <span class="info-box-number"><?php if(isset($highway_records[0])){ echo $highway_records[0]->highway_officer; } else{ echo 0; }?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        </div>
        
      </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
