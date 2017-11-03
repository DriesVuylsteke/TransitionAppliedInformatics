highway(1,2,c).
highway(2,3,a).
highway(1,3,b).
highway(3,5,a).
highway(3,4,c).
highway(5,4,d).

tour(T) :-
	check(),
	findall(highway(X,Y,Z),highway(X,Y,Z),Highways),
	findall(Path,findPath(1,none,Highways, 1, Path), [Current|Tail]),
	smallestPath(Current,Tail,T).

smallestPath(Current,[],Current).
smallestPath(Current,[Next|Tail], Result) :-
	( 
		Current @< Next
		->
		smallestPath(Current,Tail,Result)
		;
		smallestPath(Next,Tail,Result)
	).
findPath(NodeNr,_,[],NodeNr, []).
findPath(NodeNr, LastColour, Roads, Goal, CurrentPath):-
	member(Candidate, Roads),
	validRoad(NodeNr,LastColour,Candidate),
	select(Candidate,Roads,FilteredRoads),
	(
		highway(NodeNr,NextNodeNr,NextColour) = Candidate;
		highway(NextNodeNr,NodeNr,NextColour) = Candidate
	),
	findPath(NextNodeNr, NextColour, FilteredRoads, Goal, NextPath),
	CurrentPath = [NextNodeNr-NextColour| NextPath].
	
validRoad(NodeNr, LastColour, highway(X,_,Z)) :-
	NodeNr == X,
	LastColour \== Z.
validRoad(NodeNr, LastColour, highway(_,Y,Z)) :-
	NodeNr == Y,
	LastColour \== Z.
	
	
check() :-
	%check that all are even
	findall(X,highway(X,_,_),NodesFirst),
	findall(Y,highway(_,Y,_),NodesSecond),
	append(NodesFirst,NodesSecond, Nodes),
	sort(Nodes,UniqueNodes),
	evenNodes(UniqueNodes),
	%Check color outs
	[_|NodesWithoutOne] = UniqueNodes,
	checkColorOuts(NodesWithoutOne).
	
evenNodes([]).
evenNodes([X|Tail]) :-
	findall(X,highway(X,_,_),NodesFirst),
	findall(X,highway(_,X,_),NodesSecond),
	append(NodesFirst,NodesSecond, Nodes),
	length(Nodes,Count),
	0 is Count mod 2,
	evenNodes(Tail).

checkColorOuts([]).
checkColorOuts([Nr|Tail]) :-
	findall(C,highway(Nr,_,C);highway(_,Nr,C),Colours),
	length(Colours,Connections),
	sort(Colours,ColoursSorted),
	nodeColours(ColoursSorted, Nr, Connections),
	checkColorOuts(Tail).

nodeColours([],_,_).
nodeColours([Colour|Colours],NodeId, Connections) :-
	findall(Colour,highway(NodeId,_,Colour);highway(_,NodeId,Colour),Roads),
	length(Roads,Occurences),
	LeftOver is Connections - Occurences,
	Occurences =< LeftOver,
	nodeColours(Colours, NodeId, Connections).
