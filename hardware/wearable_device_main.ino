#include <Wire.h>


const byte rxPin = 22;
const byte txPin = 23;
String reply;
const byte LEDpin1 = 2;
const byte LEDpin2 = 4;


const int freq = 1000;
const int ledChannel1 = 0;
const int ledChannel2 = 1;
const int resolution = 8;

void setup() {

  Serial.begin(19200);
  while (!Serial) ; // wait for Arduino Serial Monitor to open
  Serial.println("Starting program..");
  Serial2.begin(57600, SERIAL_8N1, rxPin, txPin);
  Serial.println("LoRa tx baud 57600..");
  ledcSetup(ledChannel1, freq, resolution);
  ledcSetup(ledChannel2, freq, resolution);

  ledcAttachPin(LEDpin1, ledChannel1);
  ledcAttachPin(LEDpin2, ledChannel2);

      ledcWrite(ledChannel1, 0);
            ledcWrite(ledChannel2, 0);
}

void loop() {


  while (Serial2.available()) {
    reply = Serial2.readStringUntil('\n');
    Serial.println(reply);

    // CASE - GO RIGHT
    if (reply.indexOf("RR") > 0) {

      //      digitalWrite(LEDpin4, HIGH);
      ledcWrite(ledChannel1, 167);
      delay(350);
      ledcWrite(ledChannel1, 0);
    }
    // CASE - GO LEFT
    else if (reply.indexOf("L") > 0) {

      //  digitalWrite(LEDpin3, HIGH);
      ledcWrite(ledChannel2, 167);
      delay(350);
      ledcWrite(ledChannel2, 0);
    }
    // CASE - GO FORWARD
    else if (reply.indexOf("F") > 0) {
      ledcWrite(ledChannel1, 167);
      ledcWrite(ledChannel2, 167);
      delay(750);
      ledcWrite(ledChannel1, 0);
      ledcWrite(ledChannel2, 0);
      //  digitalWrite(LEDpin2, HIGH);
    }
    // CASE - STOP
    else if (reply.indexOf("S") > 0) {
    int i = 0;
      while(i<4){
        for(int dutyCycle = 0; dutyCycle <= 228; dutyCycle++){
    // changing the LED brightness with PWM
    ledcWrite(ledChannel1, dutyCycle);
        ledcWrite(ledChannel2, dutyCycle);
    delay(1); 
}
for(int dutyCycle = 228; dutyCycle >= 0; dutyCycle--){
    // changing the LED brightness with PWM
    ledcWrite(ledChannel1, dutyCycle);
        ledcWrite(ledChannel2, dutyCycle);
    delay(1); 
}
i++;
      }


    //  digitalWrite(LEDpin1, HIGH);
    }

  }
}
