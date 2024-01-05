import com.fasterxml.jackson.annotation.JsonProperty;

public class Motion {

    @JsonProperty("sampleid")
    public int id;
    @JsonProperty("accx")
    public float AccX;
    @JsonProperty("accy")
    public float AccY;
    @JsonProperty("accz")
    public float AccZ;

    @JsonProperty("gyrox")
    public float GyroX;
    @JsonProperty("gyroy")
    public float GyroY;
    @JsonProperty("gyroz")
    public float GyroZ;

    @JsonProperty("drivingstyle")
    public String type;

    @JsonProperty("stamp")
    public int timestamp;
    public Motion()
    {
        setId(0);
        setType("0");
        setTimestamp(0);
        setGyroX(0);
        setGyroY(0);
        setGyroZ(0);
        setAccX(0);
        setAccY(0);
        setAccZ(0);
    }

    public Motion(int id, float accx, float accy, float accz, float gyrox, float giroy, float gyroz, String type, int time)
    {
        setId(id);
        setAccX(accx);
        setAccY(accy);
        setAccZ(accz);
        setGyroX(gyrox);
        setGyroY(giroy);
        setGyroZ(gyroz);
        setTimestamp(time);
        setType(type);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccX(float accX) {
        AccX = accX;
    }

    public void setAccY(float accY) {
        AccY = accY;
    }

    public void setAccZ(float accZ) {
        AccZ = accZ;
    }

    public void setGyroX(float gyroX) {
        GyroX = gyroX;
    }

    public void setGyroY(float gyroY) {
        GyroY = gyroY;
    }

    public void setGyroZ(float gyroZ) {
        GyroZ = gyroZ;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public float getAccX() {
        return AccX;
    }

    public float getAccY() {
        return AccY;
    }

    public float getAccZ() {
        return AccZ;
    }

    public float getGyroX() {
        return GyroX;
    }

    public float getGyroY() {
        return GyroY;
    }

    public float getGyroZ() {
        return GyroZ;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }
}
