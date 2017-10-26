# Session 2: Optimal Search

## Oefening 1: A*

StartQueue: {S(17)}

Steps:

1.  {~~SC(14)~~, SA(16), SB(18)}
2.  {~~SA(16)~~, SCD(18), SB(18)}
3.  {~~SAE(16)~~, SCD(18), SB(18)}
4.  {~~SAEF(17)~~, SCD(18), SB(18)}
5.  {~~SCD(18)~~(kost te groot), SB(18), SAEFG(19)}
6.  {~~SB(18)~~, SAEFG(19)}
7.  {~~SBD(14)~~, SBE(15), SAEFG(19)}
8.  {~~SBE(15)~~, SBDF(19), SAEFG(19)}
9.  {~~SBEF(16)~~, SBDF(19), SAEFG(19)}
10. {SBEFG(18), SBDF(19), SAEFG(19)}

Fastest path: SBEFG

## Oefening 2: I D A*

1. queue: {S}, f-bound = 0, f-new = Inf
    1. {~~SA(10)~~, ~~SB(12)~~, ~~SC(12)~~} (f(path) > f-bound) f-new = 10

2. queue: {S}, f-bound = 10, f-new = Inf
    1. {__SA(10)__, ~~SB(12)~~, ~~SC(12)~~} f-new = 12
    2. {__SAD(11)__} f-new = 11
    3. {~~SADB(19)~~} f-new = 11

3. queue: {S}, f-bound = 11, f-new = Inf
    1. {__SA(10)__, ~~SB(12)~~, ~~SC(12)~~} f-new = 12
    2. {__SAD(11)__} f-new = 12
    3. {~~SADB(19)~~} f-new = 12

4. queue: {S}, f-bound = 12, f-new = Inf
    1. {__SA(10)__, SB(12), SC(12)} f-new = Inf
    2. {__SAD(11)__, SB(12), SC(12)} f-new = Inf
    3. {~~SADB(19)~~, __SB(12)__, SC(12)} f-new = 19
    4. {~~SBG(13)~~, __SC(12)__} f-new = 13
    5. {~~SCG(14)~~} f-new = 13

5. queue: {S}, f-bound = 13, f-new = Inf
    1. {__SA(10)__, SB(12), SC(12)} f-new = Inf
    2. {__SAD(11)__, SB(12), SC(12)} f-new = Inf
    3. {~~SADB(19)~~, __SB(12)__, SC(12)} f-new = 19
    4. {__SBG(13)__, SC(12)} f-new = 19

6. GOAL REACHED

## Oefening 3: S M A*

Right way now

Hold 4 nodes

startqueue: {S(12)}
tussen haken: eerst eigen F-waarde, dan onthoude F-waarde.
kinderen worden gekozen op alfabetisch volgorde.
Sorteren nadat alle kinderen behandeld zijn.

1.  {S(12), SA(15)}
2.  {S(12), SB(13), SA(15)}
3.  f-waarde veranderen van S + sort
4.  {SB(13), S(13), SA(15)}
5.  Kinderen van SB (laagste f)
6.  {SBD(18), SB(13), S(13), SA(15)}
7.  delete hoogste en onthoud deze in parent
8.  {SBG(24), SB(13,18), S(13), SA(15)}
9.  Alle kinderen gedaan. F-waarde veranderen + sort
10. {SA(15), S(18), SB(18,18), SBG(24)}
11. SA is de laagste. Verwijder hoogste (moet niet onthouden worden omdat 18 < 24)
12. {SAC(17), SA(15), S(18), SB(18,18)}
13. Hoogste verwijderen en onthouden
14. {SAG(20), SAC(17), SA(15), S(18,18)}
15. F-waarde veranderen + sort
16. {SAC(17), SA(17), S(17,18), SAG(20)}
17. Door gaan met SAC. hoogste verwijderen en onthouden.
18. {SACE(17), SAC(17), SA(17,20), S(17,18)}
19. SACE is geen eind node en alle nodes zijn gebruikt dus verander f-waarde naar Inf
20. {SAC(17), SA(17,20), S(17,18), SACE(Inf)}
21. Volgende kind. hoogste verwijderen en onthouden.
22. {SACG(21), SAC(17,Inf), SA(17,20), S(17,18)}
23. SACG is een einde. F-waardes veranderen. + sort
24. {S(18,18), SA(20,20), SAC(21,Inf), SACG(21)}
25. beste Node is de node onthouden door S
26. {SB(13), S(18,18), SA(20,20), SAC(21,Inf)}
27. F waardes veranderen + sort
28. {SB(13), S(13,18), SA(20,20), SAC(21,Inf)}
29. kinderen bekijken van SB.
30. {SBD(18), SB(13), S(13,18), SA(20,20)}
31. SA verwijderen en onthouden
32. {SBG(24), SBD(18), SB(13), S(13,20)}
33. f-waardes veranderen + sort
34. {SBD(18), SB(18), S(18,20), SBG(24)}
35. kinderen van SBD bekijken. SBG onthouden
36. {SBDG(19), SBD(18), SB(18,24), S(18,20)}
37. SBDG verwijderen, onthouden en volgende kind bekijken
38. {SBDH(17), SBD(18,19), SB(18,24), S(18,20)}
39. SBDH is geen einde. F-waarde op Inf zetten.
40. {SBDH(Inf), SBD(18,19), SB(18,24), S(18,20)}
41. F-waardes aanpassen + sort
42. {SBD(19,19), SB(19,24), S(19,20), SBDH(Inf)}
43. Hoogste verwijderen en doorgaan met best onthouden node SBDG
44. {SBDG(19), SBD(19,19), SB(19,24), S(19,20)}
45. SBDG is heeft nog steeds de beste f-waarde en is een eind node -> einde van algorithme.

## Oefening 4: bewijs

Nodes: S - N - P - G

TB: h(N) <= cost(N,P) + h(P) (Opgave vertaald)
    =>
    f(N) <= f(P)

f(N) = C(S...N) + h(N)
f(P) = C(S...P) + h(P)

h(N) <= C(N-P) + h(P)
<=> f(N) - C(S...N) <= C(N-P) + h(P)
<=> f(N) <= C(S...N) + C(N-P) + h(P)
<=> f(N) <= C(S...P) + h(P)
<=> f(N) <= f(P)
