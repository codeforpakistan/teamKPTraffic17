<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?php echo $heading; ?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Live Traffic Updates</li>
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
              <h3 class="box-title">Routes Status List</h3>
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
                  <th>Route</th>
                  <th>Route Status</th>
                  <th>Time Updated</th>
                  <th>Action</th>
                </tr>
                </thead>
                
                <tbody>
                <?php
                    $i=1;
                    if(isset($data)) foreach($data as $row){
                ?>
                <tr>
                  <td><?= $i;?></td>
                  <td><?= $row->route_name;?></td>
                  <td><?= $row->route_status;?></td>
                  <td><?= date('d M Y - H:i:s', strtotime($row->updated_time));?></td>
                  <td>
                      <a href="admin/edit_route/<?=$row->route_update_id?>" class="btn btn-primary"><i class="fa fa-pencil-square-o"></i></a>
                      <!--<a onclick="return confirm('Are you sure you want to delete this?');" href="admin/delete_route/<?=$row->route_update_id?>" class="btn btn-danger"><i class="fa fa-trash-o"></i></a>-->
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
                  <th>Route</th>
                  <th>Route Status</th>
                  <th>Time Updated</th>
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
