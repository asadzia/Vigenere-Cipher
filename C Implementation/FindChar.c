/*
* A program for finding the characters of a candidate key used to encrypt a plaintext
* according to the Vigenere Cipher scheme.
*
*
* Created By: Asad Zia
*/

#include <stdio.h>

#define PERIOD 7                   /* The period was found to be 7 from the program Period.c */

int main()
{
  FILE *infile;                    /* The file consisting of the ciphertext */
  unsigned int ciph_char;          /* This is to store the chars read from the file */
  int i = 0, j;                    /* The common iterators used in this program */
  int cipher_chars;                /* This stores the number  of characters in a sequence for each iteration */
  int totalalphabets;              /* This variable gives us the total number of writable characters found in each sequence */
  unsigned int val;                /* This stores the value of every byte after XOR-ing */
  unsigned int shift;              /* This is used to XOR with each sequence of bytes as it assumes every one of the 256 ASCII values */ 
  unsigned int ctext[940];         /* The buffer to store the cipertext */
  unsigned int stream[1250];       /* This will hold the characters for each sequence being tested. */
  int pfreq2[59];                  /* This keeps track of the frequency of all possible characters for each sequence */
  
  float pfreq[59] =                           /* This holds the real-character frequencies of the English language */ 
        {5.98, 1.05, 1.73, 3.32, 9.53,        /* lower-case english alphabet frequency */
		     1.71, 1.44, 4.99, 4.83, 0.067, 
		     0.568, 3.02, 1.90, 5.24, 5.85,
		     1.20, 0.072, 4.42, 4.75, 6.85,
		     2.18, 0.74, 1.65, 0.112, 1.49,
		     0.04,
         0.26, 0.11, 0.11, 0.097, 0.15,       /* upper-case english alphabet frequency */
         0.078, 0.078, 0.157, 0.485, 0.038,
         0.030, 0.11, 0.13, 0.12, 0.14, 0.089,
         0.01, 0.116, 0.19, 0.30, 0.039, 0.028,
         0.12, 0.008, 0.05, 0.004, 
         17.93, 1.57, 0.13, 0.082, 0.094, 0.19, 0.88}; /* punctuation frequency */

 // float score;                    /* This stores the summation of the score after the apparent and real frequencies are multiplied */
  float max_score;                  /* This holds the maximum score after XOR-ing a byte of the key and cipertext sequence */
  unsigned int req_char;            /* This holds the bytes in hexadecimal representation of the required Key character */

  int count = 0;            
  infile = fopen("ctext.txt", "r");

  while (fscanf(infile, "%2x", &ciph_char) != EOF)
  {
    ctext[i] =  (unsigned int)ciph_char;
    ++count;
    ++i;
  }

  printf("The Key: ");

  /* A loop for determining all the bytes of the key */
  for (j = 0; j < PERIOD; ++j) 
  {
      cipher_chars = 0;
      max_score = 0;

      for (i = j; i < count; i = i + PERIOD) 
      {
          /* Take out the 7th character of the ciphertext */
          stream[i/PERIOD] = (unsigned int)ctext[i]; 
          cipher_chars++;
      }

      /* Testing a key byte with each of the 256 ASCII characters */
      for (shift = 0; shift < 256; ++shift) 
      {
          for (i = 0; i < 59; ++i) 
          {
              pfreq2[i]= 0;
          }

          /* The totalalphabets variable is the number of writable characters found in the plaintext,
             where a particular shift is used */
          totalalphabets=0;
       
          for (i = 0; i < cipher_chars; ++i) 
          {
              val = stream[i] ^ shift;

              if ((val >= 'a') && (val <= 'z')) 
              {
                  pfreq2[val - 'a']++;
        	        totalalphabets++;
              }
              else if ((val >= 'A') && (val <= 'Z')) 
              {
                  pfreq2[val- 'A' + 26]++;
                  totalalphabets++;
                }
              else if (val == ' ')
              {
                  pfreq2[52]++;
                  totalalphabets++;
              }
              else if (val == ',')
              {
                  pfreq2[53]++;
                  totalalphabets++;
              }
              else if (val == ':')
              {
                  pfreq2[54]++;
                  totalalphabets++;
              }
              else if (val == '!')
              {
                  pfreq2[55]++;
                  totalalphabets++;
              }
              else if (val == '?')
              {
                  pfreq2[56]++;
                  totalalphabets++;
              }
              else if (val == ';')
              {
                  pfreq2[57]++;
                  totalalphabets++;
              }
              else if (val == '.')
              {
                  pfreq2[58]++;
                  totalalphabets++;
              }
          }

          /* The following method could be used by multiplying the real letter frequencies with the apparent frequencies */
          /* However, I have just found out the total number of visible english alphabets in my sequences and then I have
             chosen the key byte which gives the maximum number of visible english alphabets. */
          //score = 0.0;

          /*if (totalalphabets == 0)
          {
             continue;
          }*/
          
          /*for (i = 0; i < 59; ++i) 
          {
              score += pfreq[i] * ((float) pfreq2[i] / totalalphabets);
          }
          if (score>0.05) 
          {
                // if totalalphabets is too small then we know the shift is not correct
                // otherwise, choose the shift that maximizes the score 
              printf("shift: %2X, totalalphabets: %d, score: %f\n", shift, totalalphabets, score);
          }*/

          if (max_score < totalalphabets)
          {
            max_score = totalalphabets;
            req_char = shift;
          }

      }
      printf("%2X ", req_char);
  }

  fclose(infile);

  return 0;
}
