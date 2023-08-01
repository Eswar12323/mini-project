package carpooling;



public class Ride {
 private int rideId;
 private String source;
 private String destination;
 private String date;
 private String time;
 private int availableSeats;
 private String contactNumber;

 public Ride(int rideId, String source, String destination, String date, String time, int availableSeats, String contactNumber) {
     this.rideId = rideId;
     this.source = source;
     this.destination = destination;
     this.date = date;
     this.time = time;
     this.availableSeats = availableSeats;
     this.contactNumber = contactNumber;
 }

 // Getters and Setters
 public int getRideId() {
     return rideId;
 }

 public void setRideId(int rideId) {
     this.rideId = rideId;
 }

 public String getSource() {
     return source;
 }

 public void setSource(String source) {
     this.source = source;
 }

 public String getDestination() {
     return destination;
 }

 public void setDestination(String destination) {
     this.destination = destination;
 }

 public String getDate() {
     return date;
 }

 public void setDate(String date) {
     this.date = date;
 }

 public String getTime() {
     return time;
 }

 public void setTime(String time) {
     this.time = time;
 }

 public int getAvailableSeats() {
     return availableSeats;
 }

 public void setAvailableSeats(int availableSeats) {
     this.availableSeats = availableSeats;
 }

 public String getContactNumber() {
     return contactNumber;
 }

 public void setContactNumber(String contactNumber) {
     this.contactNumber = contactNumber;
 }

 @Override
 public String toString() {
     return "Ride [rideId=" + rideId + ", source=" + source + ", destination=" + destination + ", date=" + date
             + ", time=" + time + ", availableSeats=" + availableSeats + ", contactNumber=" + contactNumber + "]";
 }
}
