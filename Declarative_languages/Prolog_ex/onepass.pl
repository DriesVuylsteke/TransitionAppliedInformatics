dit is niet de modeloplossing, maar mijn variant (die ook werkt)

translate([]-Assigned,Assigned):- !.

translate([def(A)|Tail]-Assigned,L) :-
	(
		select(asgn(A,N), Assigned,AssignedCut) %passes if asgn was found
		->
		(	%use(A) was called before
			%find our ID by finding the amount of initialised asgn
			findall(Num,(member(asgn(_,Num), AssignedCut),number(Num)),AssignedList),
			length(AssignedList, AssignedListLength),
			%add fill in the variables
			ResultNum is AssignedListLength + 1,
			N = ResultNum,
			append(AssignedCut,[asgn(A,N)], Extended)
		)
		;
		(	%use(a) wasn't called before, time to add a new assignment
			%find our ID by finding the amount of initialised asgn
			findall(Num,
				(member(asgn(_,Num), Assigned),
				number(Num)),AssignedList),
			length(AssignedList, AssignedListLength),
			%add fill in the variables
			ResultNum is AssignedListLength + 1,
			append(Assigned,[asgn(A,ResultNum)], Extended)
		)
	),
	translate(Tail-Extended,L).
	
translate([use(A)|Tail]-Assigned,L) :-
	% retrieve the list of all assigned variables
	findall(asgn(ID,Num),(member(asgn(ID,Num), Assigned),number(Num)),AssignedList),
	(
		member(asgn(A,N),AssignedList) % A has been assigned
		->
		append(Assigned,[use(N)], Extended),
		translate(Tail-Extended,L)
		;
		%A hasn't been assigned -> create a new asgn(A,Number)
		append(Assigned,[asgn(A,NewNumber)], Extended), % this will get cleaned up later
		append(Extended,[use(NewNumber)], NewExtended),
		translate(Tail-NewExtended,L)
	).
	
