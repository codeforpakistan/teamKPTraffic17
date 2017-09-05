
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?= $heading;?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">License Verification</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
    
    <section style="padding: 15px 15px 0 15px;">
        <div class="row">
            <div class="col-xs-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                      <h3 class="box-title">Add License Info through Excel Sheet</h3>
                    </div>
                    
                    <!-- session message -->
                    <?php if($this->session->flashdata('msg')):?>
                    <div class="callout callout-success" id="msg">
                        <p align="center" style="position:relative; font-size:16px;">
                            <?=$this->session->flashdata('msg')?>
                        </p>
                    </div>
                    <?php endif;?>
                
                    <!-- Upload excel file -->
                    <form class="form-horizontal" action="<?php echo base_url();?>excel/upload/" method="post" enctype="multipart/form-data">
                       <div class="box-body">
                            <div class="form-group">
                              <label for="fileupload" class="col-sm-3 control-label">Upload Excel Sheet</label>

                              <div class="col-sm-6">
                                  <input type="file" name="file"/>
                                  <input type="submit" name="submit" class="btn btn-primary" value="Upload file" style="margin-top:8px;"/>
                              </div>
                            </div> 
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
      <!-- Main content dataTable -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
            <!-- Horizontal Form -->
            <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">Add New License Info</h3>
                </div>
                <!-- /.box-header -->
                
                <!-- form start -->
                <form class="form-horizontal" action="<?php echo base_url()?>admin/add_license_process" method="post" enctype="multipart/form-data">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="lic_number" class="col-sm-3 control-label">Driving License Number</label>
                      
                      <div class="col-sm-6">
                          <input type="text" class="form-control" name="lic_number" id="lic_number" maxlength="12" placeholder="Enter Driving License Number">
                          <?php echo '<span class="error">'. form_error('lic_number').'</span>'; ?>
                      </div>
                    </div>              
                    
                    <div class="form-group">
                      <label for="cnic" class="col-sm-3 control-label">CNIC</label>
                      
                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="cnic" id="cnic" maxlength="15" placeholder="Enter CNIC">
                        <?php echo '<span class="error">'. form_error('cnic').'</span>'; ?>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label for="name" class="col-sm-3 control-label">Name</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" id="name" placeholder="Enter Name">
                        <?php echo '<span class="error">'. form_error('name').'</span>'; ?>
                      </div>
                    </div>  
                    
                    <div class="form-group">
                      <label for="father_name" class="col-sm-3 control-label">Father Name</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="father_name" id="father_name" placeholder="Enter Father Name">
                        <?php echo '<span class="error">'. form_error('father_name').'</span>'; ?>
                      </div>
                    </div>
                            
                    <div class="form-group">
                      <label for="lic_type" class="col-sm-3 control-label">License Type</label>
                        
                      <div class="col-sm-6">
                         <select name="lic_type" id="lic_type" class="form-control" onchange="document.getElementById('selected_text').value=this.options[this.selectedIndex].text">
                            <option value="0">Select License Type</option>
                            <?php
                                if(isset($lic_type)) foreach($lic_type as $row){ 
                            ?>
                                <option value="<?= $row->type_id;?>"><?= $row->license_type;?></option>
                            <?php }?>
                          </select>
                          <?php echo '<span class="error">'. form_error('lic_type').'</span>'; ?>
                        <!--<input type="text" class="form-control" name="lic_type" id="lic_type" required placeholder="Enter license Type">-->
                        <input type="hidden" name="selected_text" id="selected_text" value="" />
                      </div>
                    </div>
                     
                     <div class="form-group">
                      <label for="lic_expiry_date" class="col-sm-3 control-label">License Expiry Date</label>

                      <div class="col-sm-6">
                        <div class="input-group date">
                          <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                          </div>
                          <input type="text" class="form-control" name="lic_expiry_date" id="datepicker" id="lic_expiry_date" placeholder="Enter license Expiry Date">
                        </div>
                          <?php echo '<span class="error">'. form_error('lic_expiry_date').'</span>'; ?>
                      </div>
                    </div>          
                    
                    <div class="form-group">
                      <label for="status" class="col-sm-3 control-label">License Status</label>
                      
                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="status" value="Verified" id="status" disabled placeholder="Enter status">
                      </div>
                    </div>    
                     
                     <div class="form-group">
                      <label class="col-sm-3 control-label">Select District</label>
                      <div class="col-sm-6">
                          <select name="district" id="district" class="form-control "onchange="document.getElementById('select_district').value=this.options[this.selectedIndex].text">
                            <option value="0">Select District</option>
                            <?php
                                if(isset($districts)) foreach($districts as $row){ 
                            ?>
                                <option value="<?= $row->district_id;?>"><?= $row->name;?></option>
                            <?php }?>
                          </select>
                          <?php echo '<span class="error">'. form_error('district').'</span>'; ?>
                          <input type="hidden" name="select_district" id="select_district" value="" />
                      </div>
                    </div>
                     <!--<div class="form-group">
                      <label for="district" class="col-sm-3 control-label">District</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="district" id="district" required placeholder="Enter District">
                      </div>
                    </div>-->
                             
                  </div>
                  <!-- /.box-body -->
                  <div class="box-footer">
                    <div class="col-sm-offset-7 col-sm-3">
                       <a href="<?php echo base_url().'admin/get_license_list'; ?>" class="btn btn-default">Cancel</a>
                        <button type="reset" class="btn btn-default">Reset</button>
                        <button type="submit" class="btn btn-info">Submit</button>
                    </div>
                  </div>
                  <!-- /.box-footer -->
                </form>
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
