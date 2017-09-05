
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?= $heading;?>
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
            <!-- Horizontal Form -->
            <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">Add Division</h3>
                </div>
                <!-- /.box-header -->
                <!-- session message -->
                <?php if($this->session->flashdata('msg')):?>
                <div class="callout callout-danger" id="msg">
                    <p align="center" style="position:relative; font-size:16px;">
                        <?=$this->session->flashdata('msg')?>
                    </p>
                </div>
                <?php endif;?>
                
                <!-- form start -->
                <form class="form-horizontal" action="<?= $action;?>" method="post" enctype="multipart/form-data">
                  <div class="box-body">
                  
                   <div class="form-group">
                      <label for="name" class="col-sm-3 control-label">Division Name</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" required id="name" placeholder="Enter Name Here">
                      </div>
                    </div> 
                    
                  </div>
                  <!-- /.box-body -->
                  <div class="box-footer">
                    <div class="col-sm-offset-7 col-sm-3">
                        <a href="<?php echo base_url().'Admin_emergency/show_divisions'; ?>" class="btn btn-default">Cancel</a>
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
