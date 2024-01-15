
(* binary arithmetical operators implicitly associate to the left *)
5 - 2 -1 ;;            (* returns 2 *) 
(5 - 2) - 1 ;;         (* returns 2 *)    
5 - (2 - 1) ;;         (* returns 4 *)

4 / 2 / 2 ;;           (* returns 1 *)
(4 / 2) / 2 ;;         (* returns 1 *)
4 / (2 / 2) ;;         (* returns 4 *)
(* so, subtraction '-' and integer division '/' are NOT associative *)

(* use keyword 'let' to name a value *)
let x = 5 ;;  (* for the rest of the script we can use 'x' as a name for '5' *)
let y = x + 3 ;; (* for the rest of the script we can use 'y' as a name for '8' *)

(* we can limit the scope of a let-introduced name *)
let z = x + 3 in z + 10 ;;  (* the scope of the bound 'z' is 'z + 10' *)

(* equivalent ways of defining function 'squared_int' which squares an integer *)
let squared_int x = x * x ;; 
let squared_int_1 (x : int) = x * x ;; 
let squared_int_2 (x : int) : int = x * x ;; 
let squared_int_3 = fun x -> x * x ;; 
let squared_int_4 : int -> int = fun (x : int) -> x * x ;; 

(* for the function 'squared_float' we need to explicitly insert the types *)
let squared_float (x : float) : float = x *. x ;; 
(* 'squared_float 3' causes an error but 'squared_float 3.' does not -- why? *)

(* two different equalities: structural '=' and physical '==' *)
3 = 3 ;;              (* returns 'true' *)
3 == 3 ;;             (* returns 'true' *) 
3.0 = 3.0 ;;          (* returns 'true' *) 
3.0 == 3.0 ;;         (* returns 'false' *) 
(* 'e1 == e2' implies 'e1 = e2' but not necessarily the other way around --
   this is subtle and we will come back to this in some later session *)

(* two different inequalities: structural '<>' and physical '!=' *)
3 <> 4 ;;             (* returns 'true' *)
3 != 4 ;;             (* returns 'true' *)
3.0 <> 4.0 ;;         (* returns 'true' *)
3.0 != 4.0 ;;         (* returns 'true' *)
(* this is all very confusing, especially if you are familiar with other
  programming languages that use or do not use '!=' instead of '<>',
  but with practice you will get a sense of what to use -- and in case
  of doubt, start with '=' instead of '==' and with '<>' instead of '!=' *)

(* working with boolean values *)
let b = true ;;
b && false ;;
true || false ;;
1 = 2 ;;   (* = not == for equality comparison *)
1 <> 2 ;;  (* <> not != for not equal *)

(* working with floats *)
4.5 ;; 
4.5 +. 4.3 ;; (* operations are +. etc not just + which is for ints only *)
30980314323422L ;; (* 64-bit integers *)

(* working with characters and strings *)
'c' ;; 
"strings";;

(* definition of a recursive function *)
let rec fib n =     (* the 'rec' keyword needs to be added to allow recursion *)
  if n <= 0 then 0
  else if n = 1 then 1
  else fib (n - 1) + fib (n - 2);; (* note everything is an expression, there is no 'return' *)

(* working with anonymous functions *)
let add1 x = x + 1;; (* a normal add1 definition, no anonymous function here *)
let anon_add1 = (fun x -> x + 1);; (* equivalent anonymous version; 'x' is argument here *)
((fun x -> x + 1) 4) * 7;; (* you can inline an anonymous function *)

