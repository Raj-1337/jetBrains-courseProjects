# Encryption - Decryption

* This is a simple Java CLI Encryption-Decryption program incorporating Factory design pattern.
* Two variants of Caesar's cipher is used:  
    * Type 1 - shift (simple/original) - It shifts the letters through alphabets both a-z, A-Z. Only letters are manipulated, punctuations and other symbols are left as it is.
    * Type 2 - unicode shift - It shifts every character using unicode value by the given number.
* Program arguments:
    * **`mode`** : `enc` - encryption | `dec` - decryption, default - `enc`.
    * **`key`** : `number` - any number denoting no. of shifts, default - `0`.
    * **`alg`** : `shift` - type 1 | `unicode` - type 2, default - `shift`.
    * **`data`** : `string` - Input text, not needed if `in` is specified.
    * **`in`** : `string` - Name of the input file. example - "in.txt", not needed if `data` is specified.
    * **`out`** : `string` - Name of the output file. example - "out.txt", if not specified output will be redirected to console.
    > **NOTE:** input/output files must be in the same directory as the jar. 
* The `jar` version of the project is located under, `out -> artifacts -> Encryption_Decryption_jar`. 