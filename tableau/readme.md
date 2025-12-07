<Sheet Name> => key performance indicatior status

IF SUM([Sales]) >= SUM([Targets]) THEN "above target"
ELSEIF SUM([Sales]) >= 0.9 * SUM([Targets]) THEN "near target"
ELSE "below target"
END





<Sheet Name>
{ FIXED [Country/Region] : COUNTD([Order ID]) }
distince order id only for a Country/Region

count - all order id's

{ FIXED [Country/Region] : AVG({ FIXED [Order ID] : SUM([Sales]) } ) }





average order value 
{ FIXED [Country/Region] : AVG({ FIXED [Order ID] : SUM([Sales]) } ) }


distinceOrdersForCountry
{ FIXED [Country/Region] : COUNTD([Order ID]) }


nonDistinctOrderIdPerCountry
{ FIXED [Country/Region] : COUNT([Order ID]) }


persentageOfTotal
(SUM([Sales])/TOTAL(SUM([Sales])))


ProfitMargin
([Profit] / [Sales]) * 100



sales year on year
LOOKUP(SUM([Sales]), -1)



yearOnYearSalesGrowth
((SUM([Sales]) - LOOKUP(SUM([Sales]), -1)) / LOOKUP(SUM([Sales]), -1)) * 100