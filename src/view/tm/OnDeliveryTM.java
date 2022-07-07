package view.tm;

public class OnDeliveryTM {
    private String vehicleNo;
    private String vehicleType;
    private String DriverName;
    private String leftTime;

    public OnDeliveryTM(String vehicleNo, String vehicleType, String driverName, String leftTime) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        DriverName = driverName;
        this.leftTime = leftTime;
    }

    public OnDeliveryTM() {
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public String getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(String leftTime) {
        this.leftTime = leftTime;
    }

    @Override
    public String toString() {
        return "OnDelivery{" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", DriverName='" + DriverName + '\'' +
                ", leftTime='" + leftTime + '\'' +
                '}';
    }
}
