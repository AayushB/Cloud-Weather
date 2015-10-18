// This #include statement was automatically added by the Particle IDE.
#include "LiquidCrystal.h"
LiquidCrystal lcd(D5, D4, D3, D2, D1, D0);

void setup() {
  // set up the LCD's number of columns and rows: 
  lcd.begin(16,2);
  // Print a message to the LCD.
  lcd.print("Hello, Sparky!");
}

void loop() {
  // set the cursor to column 0, line 1
  // (note: line 1 is the second row, since counting begins with 0):
  //lcd.setCursor(0, 1);
  // print the number of seconds since reset:
  //lcd.print(millis()/1000);
}
