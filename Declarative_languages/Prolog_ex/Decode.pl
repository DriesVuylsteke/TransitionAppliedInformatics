%my personal solution, this is NOT a model solution

decode(N,Codes,Res) :-
	decode(N,0,Codes,Res).

decode(0,_,[],[]).
decode(N, CurNr, Codes,Res) :-
	N > 0,
	append(Current, _, Codes),
	Current \== [],
	append(Current,Next,Codes),
	NextNr is N - 1,
	NextCur is CurNr + 1,
	decode(NextNr,NextCur, Next, R1),
	containsPrefix(Current, R1),
	Res = [CurNr-Current|R1].	

containsPrefix(_,[]).
containsPrefix(Prefix, [_-Check|Tail]) :-
	\+ append(Prefix,_,Check),
	\+ append(Check,_,Prefix),
	containsPrefix(Prefix, Tail).
	
best([], _, [[]-0]).
best([Code|CodeTail], Freq, Res) :-
	best(CodeTail,Freq, [C-ResR|ResultTail]),
	value(Code, Freq, Value),
	(
		(Value < ResR ; ResR = 0)
		->
		Res = [Code-Value]
		;
		(
			Value = ResR
			->
			append([C-ResR|ResultTail],[Code-Value], Res)
			;
			Res = [C-ResR|ResultTail]
		)
	).
	
best(N, Numbers, Freqs, Res) :-
	findall(Code,decode(N,Numbers,Code), Codes),
	best(Codes, Freqs, Results),
	member(Res-_,Results).
	
value([],[],0).
value([A-Code|CodeTail], [A-Freq|FreqTail], Value) :-
	value(CodeTail, FreqTail, ValueR),
	length(Code,CodeLenth),
	CurVal is CodeLenth * Freq,
	Value is ValueR + CurVal.
	
