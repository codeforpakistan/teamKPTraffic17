package com.kptrafficpolice.trafficapp.fragments.ETest;

/**
 * Created by DzonE on 09-Jul-18.
 */

public class TestResultListModel {

    private String Question = "";
    private String Op1 = "";
    private String Op2 = "";
    private String Op3 = "";
    private String Status_op1 = "";
    private String Status_op2 = "";
    private String Status_op3 = "";
    private String Image = "";


    //create constructor to initialize values
    public TestResultListModel(String question, String op1, String op2, String op3
            , String status_op1, String status_op2, String status_op3, String image) {

        Question = question;
        Op1 = op1;
        Op2 = op2;
        Op3 = op3;
        Status_op1 = status_op1;
        Status_op2 = status_op2;
        Status_op3 = status_op3;
        Image = image;

    }

    public void setStatus_Op1(String Status_Op1) {
        this.Status_op1 = Status_Op1;
    }

    public void setStatus_Op2(String Status_Op2) {
        this.Status_op2 = Status_Op2;
    }

    public void setStatus_Op3(String Status_Op3) {
        this.Status_op3 = Status_Op3;
    }

    /*********** Get Methods ****************/

    public String getQuestion() {
        return this.Question;
    }

    /*********** Set Methods ******************/

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getOp1() {
        return this.Op1;
    }

    public void setOp1(String Op1) {
        this.Op1 = Op1;
    }

    public String getOp2() {
        return this.Op2;
    }

    public void setOp2(String Op2) {
        this.Op2 = Op2;
    }

    public String getOp3() {
        return this.Op3;
    }

    public void setOp3(String Op3) {
        this.Op3 = Op3;
    }

    public String getStatus_op1() {
        return this.Status_op1;
    }

    public String getStatus_op2() {
        return this.Status_op2;
    }

    public String getStatus_op3() {
        return this.Status_op3;
    }

    public String getImage() {
        return this.Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

}