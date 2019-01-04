package id.jmlcode.sample.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jamal on 3/13/2018.
 */

public class ResultStatement {
    @SerializedName("StartDate")private String startDate;
    @SerializedName("EndDate")private String endDate;
    @SerializedName("Currency")private String currency;
    @SerializedName("StartBalance")private String startBalance;
    @SerializedName("Data")private List<Data> dataList;

    public ResultStatement() {
        super();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(String startBalance) {
        this.startBalance = startBalance;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public static class Data{
        @SerializedName("TransactionDate")private String transactionDate;
        @SerializedName("BranchCode")private String branchCode;
        @SerializedName("TransactionType")private String transactionType;
        @SerializedName("TransactionAmount")private String transactionAmount;
        @SerializedName("TransactionName")private String transactionName;
        @SerializedName("Trailer")private String trailer;
    }
}
