/*
* A program for decrypting a ciphertext according to the Vigenere Cipher scheme.
*
*
* Created By: Asad Zia
*/

#include <stdio.h>

#define KEY_LENGTH 7 

int main()
{
  unsigned int ch;
  FILE *fpIn;
  int it = 0;
  unsigned int key[KEY_LENGTH] = {0xba, 0x1f, 0x91, 0xb2, 0x53, 0xcd, 0x3e};

  fpIn = fopen("ctext.txt", "r");

  while (fscanf(fpIn, "%2x", &ch) != EOF) {
        printf("%c", ch ^ key[it % KEY_LENGTH]);
        ++it;
  }
 
  fclose(fpIn);

  return 0;
} 
