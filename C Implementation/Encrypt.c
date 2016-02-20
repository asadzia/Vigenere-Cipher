/*
* A program for encrypting a plaintext according to the Vigenere Cipher scheme.
*
*
* Created By: Asad Zia
*/

#include <stdio.h>
#define KEY_LENGTH 7 

int main()
{
  FILE *fpIn, *fpOut;
  int it = 0;
  unsigned char ch;
  unsigned char key[KEY_LENGTH] = {0xba, 0x1f, 0x91, 0xb2, 0x53, 0xcd, 0x3e};

  /* The file which contains the plaintext */
  fpIn = fopen("ptext.txt", "r");
  /* The file which will hold the ciphertext */
  fpOut = fopen("ctext.txt", "w");

  while (fscanf(fpIn, "%c", &ch) != EOF) {
    /* a logical XOR shift is used for encryption */
    if (ch!='\n') {
      fprintf(fpOut, "%02X", ch ^ key[it % KEY_LENGTH]);    
      ++it;
      }
    }
 
  fclose(fpIn);
  fclose(fpOut);

  return 0;
} 
