% 1 getting started

father(anton,bart). 
father(anton,daan). 
father(anton,elisa). 
father(fabian,anton).

mother(celine,bart). 
mother(celine,daan). 
mother(celine,gerda). 
mother(gerda,hendrik).

% 2 siblings

sibling(X,Y) :- 
	father(F,X),
	father(F,Y),
	mother(M,X),
	mother(M,Y),
	X \== Y.

% 3 family tree

ancestor(X,Y) :- 
	father(X,Y). 

ancestor(X,Y) :-
	father(Z,Y),
	ancestor(X,Z).

% Peano

peano_plus(zero,X,X).
peano_plus(s(X),Y,s(Z)) :- peano_plus(X,Y,Z). 

min(X,zero,X).
min(s(X),s(Y),Z) :- min(X,Y,Z).

greater_than(_, zero).
greater_than(s(X),s(Y)) :- 
	greater_than(X,Y),
	X \== Y.
	
maximum(zero,X,X).
maximum(X,zero,X).
maximum(s(X),s(Y),s(Z)) :- maximum(X,Y,Z).

% TODO Make the 2 extra excercises.

% Depth

node(_,_,_).

depth(nil,0).
depth(node(L1,_,R1),D) :- 
	depth(L1,D1),
	depth(R1,D2),
	D is max(D1,D2) + 1.

% Boolean

eval(tru,tru).
eval(fal,fal).

eval(and(tru,tru),true).
eval(and(fal,tru),fal).
eval(and(tru,fal),fal).
eval(and(fal,fal),fal).

eval(or(tru,tru),tru).
eval(or(fal,tru),tru).
eval(or(tru,fal),tru).
eval(or(fal,fal),fal).

eval(not(tru),fal).
eval(not(fal),tru).

eval(and(X,Y),Z) :-
	eval(X,Z1),
	eval(Y,Z2),
	eval(and(Z1,Z2),Z).

eval(or(X,Y),Z) :-
	eval(X,Z1),
	eval(Y,Z2),
	eval(or(Z1,Z2),Z).

eval(not(X),Z) :-
	eval(X,Y),
	eval(not(Y),Z).
	
% Calculator
eval(number(X),X).
	
eval(plus(X,Y),Z) :-
	eval(X,Z1),
	eval(Y,Z2),
	Z is Z1 + Z2.

eval(min(X,Y),Z) :-
	eval(X,Z1),
	eval(Y,Z2),
	Z is Z1 - Z2.

eval(neg(X),Z) :-
	eval(X,Z1),
	Z is -Z1.

% Combining Calculator and boolean
eval(eq(X,X),tru).
eval(eq(_,_),fal).	
	
eval(true,tru).
eval(false,fal).

eval(=(X,Y),Z) :-
	eval(X,Z1),
	eval(Y,Z2),
	eval(eq(Z1,Z2),Z).
	

/**
 * MinMax tree
 * 
 * This solution seems to work but there may be a better solution.
 */
 
minmaxtree(node(C1-leaf(X),C1-leaf(_)),X).
minmaxtree(node(C1-X,C2-Y),Z) :-
	D is min(C1,C2),
	choosedir_min(D,C1-X,C2-Y,Z).

choosedir_min(D,D-leaf(X),_-leaf(_),X).
choosedir_min(D,_-leaf(_),D-leaf(X),X).

choosedir_min(D,D-X,_,Z) :-
	minmaxtree_max(X,Z).
choosedir_min(D,_,D-X,Z) :-
	minmaxtree_max(X,Z).
	
minmaxtree_max(node(C1-leaf(X),C1-leaf(_)),X).
minmaxtree_max(node(C1-X,C2-Y),Z) :-
	D is max(C1,C2),
	choosedir_max(D,C1-X,C2-Y,Z).
		
choosedir_max(D,D-leaf(X),_-leaf(_),X).
choosedir_max(D,_-leaf(_),D-leaf(X),X).

choosedir_max(D,D-X,_,Z) :-
	minmaxtree(X,Z).
choosedir_max(D,_,D-X,Z) :-
	minmaxtree(X,Z).
	
/**
 * Other solution
 */
minmaxtree(leaf(X),X).
minmaxtree(node(C1-L, C2-R), V) :-
	Dir is max(C1,C2),
	(
		(
		Dir = C2, % right is highest, or C1 = C2
		maxmintree(L,V) % go left
		)
	;
		(
		Dir = C1, % left is highest
		maxmintree(R, V) % go right
		)
	).

maxmintree(leaf(X),X).
maxmintree(node(C1-L, C2-R), V) :-
	Dir is max(C1,C2),
	(
		(
		Dir = C1, % left is highest or they are equal
		minmaxtree(L, V) % go left
		)
	;
		(
		Dir = C2, %right is highest
		minmaxtree(R,V) % go right
		)
	).

/*
 * model oplossing
 */
minmaxtree(leaf(Result), Result).
minmaxtree(node(C1-LTree, C2-_), Result) :-
	C1 =< C2,
	maxmintree(LTree, Result).

minmaxtree(node(C1-_, C2-RTree), Result) :-
	C1 > C2,
	maxmintree(RTree, Result).

maxmintree(leaf(Result), Result).
maxmintree(node(C1-_, C2-RTree), Result) :-
	C1 < C2,
	minmaxtree(RTree, Result).

maxmintree(node(C1-LTree, C2-_), Result) :-
	C1 >= C2,
	minmaxtree(LTree, Result).
