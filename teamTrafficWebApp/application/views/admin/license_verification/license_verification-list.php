
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?php echo $heading; ?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">License Verification</li>
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
              <h3 class="box-title">License Verification List</h3>
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
                  <th>Driving License Number</th>
                  <th>CNIC</th>
                  <th>Name</th>
                  <th>Father Name</th>
                  <th>License Type</th>
                  <th>License Expiry Date</th>
                  <th>District</th>
                  <th>License Status</th>
                  <th>Action</th>
                </tr>
                </thead>
                
                <tbody>
                <?php
                    $i=1;
                    if(isset($data)) foreach($data as $row){
                    //print_r($data);die;
                ?>
                <tr>
                  <td><?= $i;?></td>
                  <td><?= $row->dl_number;?></td>
                  <td><?= $row->cnic;?></td>
                  <td><?= $row->name;?></td>
                  <td><?= $row->father_name;?></td>
                  <td><?= $row->license_type;?></td>
                  <td><?= date('d-M-Y', strtotime($row->license_expiry_date));?></td>
                  <td><?= $row->district;?></td>
                  <td><?php if((date('Y-m-d') <= $row->license_expiry_date)){ 
                                echo '<span class="label label-success">License Verified</span>';
                                }else
                                    echo '<span class="label label-danger">License Expired</span>';
                    ?></td>
                  <td>
                      <a href="admin/edit_license/<?= $row->license_id?>" class="btn btn-primary"><i class="fa fa-pencil-square-o"></i></a>
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
                  <th>Driving License Number</th>
                  <th>CNIC</th>
                  <th>Name</th>
                  <th>Father Name</th>
                  <th>License Type</th>
                  <th>License Expiry Date</th>
                  <th>District</th>
                  <th>License Status</th>
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
