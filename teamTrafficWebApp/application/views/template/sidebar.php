 <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="<?php echo base_url();?>assets/images/kp-logo.png" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Admin Panel</p>
          KP TRAFFIC POLICE
        </div>
      </div>
      
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">MAIN NAVIGATION</li>
        <li class="<?= $heading == "Dashboard"?"active":"";?>">
          <a href="<?php echo base_url()."admin/dashboard"?>">
            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
            <span class="pull-right-container">
              <!--<i class="fa fa-angle-left pull-right"></i>-->
            </span>
          </a>
        </li>
        
        <li class="treeview <?= $heading == "Complaints"?"active":"";?>">
          <a href="#">
            <i class="fa fa-files-o"></i><span>Complaints</span>
            <span class="pull-right-container">
               <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="<?= $this->uri->segment(2) == "get_complaints"?"active":"";?>">
                <a href="<?= base_url().'admin/get_complaints'; ?>"><i class="fa fa-list-alt"></i> All Complaints</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "pending_complaints"?"active":"";?>">
                <a href="<?= base_url().'admin/pending_complaints'; ?>"><i class="fa fa-exclamation-circle"></i> Pending Complaints</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "inprogress_complaints"?"active":"";?>">
                <a href="<?= base_url().'admin/inprogress_complaints'; ?>"><i class="fa fa-inbox"></i> In Progress Complaints</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "completed_complaints"?"active":"";?>">
                <a href="<?= base_url().'admin/completed_complaints'; ?>"><i class="fa fa-check-square-o"></i> Completed Complaints</a>
            </li>
          </ul>
        </li>
        
        <li class="<?= $this->uri->segment(2) == "map"?"active":"";?>">
          <a href="<?= base_url().'admin/map'; ?>">
            <i class="fa fa-map-o"></i> <span>Heat Map</span>
            <span class="pull-right-container">
               <!--<i class="fa fa-angle-left pull-right"></i>-->
            </span>
          </a>
        </li>
        
        <li class="treeview <?= $heading == "Live Traffic Updates"?"active":"";?>">
          <a href="#">
            <i class="fa fa-road"></i> <span>Live Traffic Updates</span>
            <span class="pull-right-container">
               <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
<!--
            <li class="<?php //echo $this->uri->segment(2) == "add_route_update"?"active":"";?>">
                <a href="<?php //echo base_url().'admin/add_route_update'; ?>"><i class="fa fa-plus"></i> Add Route Status</a>
            </li>
-->
            <li class="<?= $this->uri->segment(2) == "routes_list"?"active":"";?>">
                <a href="<?= base_url().'admin/routes_list'; ?>"><i class="fa fa-car"></i> All Routes Status</a>
            </li>
          </ul>
        </li>
        
        <li class="treeview <?= $heading == "License Verification"?"active":"";?>">
          <a href="#">
            <i class="fa fa-id-card-o" aria-hidden="true"></i>
            <span>License Verification</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="<?= $this->uri->segment(2) == "add_license"?"active":"";?>">
                <a href="<?= base_url().'admin/add_license'; ?>"><i class="fa fa-file-text"></i> Add New License</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "get_license_list"?"active":"";?>">
                <a href="<?= base_url().'admin/get_license_list'; ?>"><i class="fa fa-list-alt"></i> License List</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "add_lic_type"?"active":"";?>">
                <a href="<?= base_url().'admin/add_lic_type'; ?>"><i class="fa fa-bus"></i> Add New License Type</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "get_lic_types"?"active":"";?>">
                <a href="<?= base_url().'admin/get_lic_types'; ?>"><i class="fa fa-file-text-o"></i> License Types List</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "add_lic_district"?"active":"";?>">
                <a href="<?= base_url().'admin/add_lic_district'; ?>"><i class="fa fa-street-view"></i> Add New District</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "get_lic_districts"?"active":"";?>">
                <a href="<?= base_url().'admin/get_lic_districts'; ?>"><i class="fa fa-list"></i> Districts List</a>
            </li>
          </ul>
        </li>
        
        <li class="treeview <?= $heading == "Traffic Education"?"active":"";?>">
          <a href="#">
            <i class="fa fa-hand-stop-o"></i>
            <span>Traffic Education</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="<?= $this->uri->segment(2) == "add_traffic_sign"?"active":"";?>">
                <a href="<?php echo base_url().'admin/add_traffic_sign'; ?>"><i class="fa fa-file-image-o"></i> Add Traffic Sign</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "view_all_signs"?"active":"";?>">
                <a href="<?php echo base_url().'admin/view_all_signs'; ?>"><i class="fa fa-th-list"></i> Show All Signs</a>
            </li>
            <!--<li><a href="#"><i class="fa fa-pencil-square-o"></i> Update Traffic Sign</a></li>-->
          </ul>
        </li>
        
        <li class="treeview <?= $heading == "Emergency Contacts"?"active":"";?>">
          <a href="#">
            <i class="fa fa-ambulance"></i> <span>Emergency Contacts</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="<?= $this->uri->segment(2) == "add_emergency"?"active":"";?>">
                <a href="<?= base_url()."admin_emergency/add_emergency"; ?>"><i class="fa fa-plus-square"></i> Add Emergency Contact</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "emergency_list"?"active":"";?>">
                <a href="<?= base_url()."admin_emergency/emergency_list"; ?>"><i class="fa fa-list"></i> Emergency Contacts List</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "add_division"?"active":"";?>">
                <a href="<?= base_url()."admin_emergency/add_division"; ?>"><i class="fa fa-plus"></i> Add Division</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "show_divisions"?"active":"";?>">
                <a href="<?= base_url()."admin_emergency/show_divisions"; ?>"><i class="fa fa-file-text-o"></i> Show Divisions</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "add_district"?"active":"";?>">
                <a href="<?= base_url()."admin_emergency/add_district"; ?>"><i class="fa fa-plus"></i> Add District</a>
            </li>
            <li class="<?= $this->uri->segment(2) == "show_districts"?"active":"";?>">
                <a href="<?= base_url()."admin_emergency/show_districts"; ?>"><i class="fa fa-file-text-o"></i> Show Districts</a>
            </li>
          </ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
