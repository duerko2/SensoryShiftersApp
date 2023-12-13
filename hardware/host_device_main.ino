
#include <Wire.h>


const byte RXD2 = 18;
const byte TXD2 = 4;

String reply = "";

// button pins setup
const byte rSwitchPin = 23;
const byte lSwitchPin = 22;
const byte fSwitchPin = 21;
const byte sSwitchPin = 19;

bool rightVal;
bool leftVal;
bool forwardVal;
bool stopVal;



void setup() {
  Serial.begin(19200);
  Serial2.begin(57600);
}

void loop() {
  rightVal = digitalRead(rSwitchPin);
  leftVal = digitalRead(lSwitchPin);
  forwardVal = digitalRead(fSwitchPin);
  stopVal = digitalRead(sSwitchPin);

  if (rightVal) {
    Serial.println("Right clicked!");
    Serial2.println("AT+SEND=1,2,RR");
    delay(1000);
  } else if (leftVal) {
    Serial.println("Left clicked!");
    Serial2.println("AT+SEND=1,1,L");
    delay(1000);
  } else if (forwardVal) {
    Serial.println("Forward clicked!");
    Serial2.println("AT+SEND=1,1,F");
    delay(1000);

  } else if (stopVal) {
    Serial.println("Stop clicked!");
    Serial2.println("AT+SEND=1,1,S");
    delay(1000);
  }

}
