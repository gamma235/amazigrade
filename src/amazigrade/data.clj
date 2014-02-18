(ns amazigrade.data)

;; It is convenient and more organized to keep a data-file that you can refer to as you work.
;; The numbers included here are just samples and should be changed according to your needs.
;; They are spaced this way for convenience in copying and pasting into excel.



;; the student's class numbers, ordered according to my classlists

(def nums [
1129
1121
1091
1086
1130
1131
1081
1063
1109
1111
1116
1092
1106
1096
1095
1082
1120
1088
1097
1077
1090
1125
1102
1101
1110
1098
1118
1079
1084
1067
1117
1039
1087
1119
1126])



;; It might sometimes happen that the official class lists are in a different order.

(def official-nums
[1063
1067
1077
1079
1081
1082
1084
1086
1087
1088
1090
1091
1092
1095
1096
1097
1098
1101
1102
1106
1109
1110
1111
1116
1117
1118
1119
1120
1125
1129
1130
1131
1126
1121])

;; student grades in vectors, ordered according to my class list

(def grades [
 	[100	70	85	71	85]
 	[100	75	85	81	85]
 	[100	70	55	54	85]
 	[100	70	60	58	65]
 	[100	60	75	55	65]
 	[75	60	80	56	75]
 	[80	60	90	48	65]
 	[80	65	70	63	55]
 	[65	65	55	49	60]
 	[100	50	60	41	60]
 	[100	60	80	59	65]
 	[75	65	60	62	60]
  [75	60	0	53	80]
 	[100	0	0	40	50]
 	[0	0	0	46	50]
 	[100	65	60	60	80]
 	[100	65	90	30	80]
 	[100	60	85	65	60]
 	[75	70		71	65]
 	[100	60	90	70	65]
 	[100	50	60	0	65]
 	[0	60	60	50	65]
 	[100	70	80	82	65]
 	[100	55	55	59	55]
 	[100	55	75	66	55]
 	[100	60	60	53	65]
 	[100	50	80	58	70]
 	[80	65	60	53	60]
 	[0	70	0	75	75]
 	[0	65	67	55	65]
 	[0	70	75	55	60]
 	[50	60	60	45]
 	[50	60	55	61	70]
 	[50	65	55	71	65]
 	[0 0 0 0 0]])


;; If you already know the final grades, but you want to store them in your data-file for subsequent modifications (e.g. bell-curves)

(def class-finals
[80
81
70
75
77
72
76
78
74
85
75
66
77
71
74
84
90
80
87
76
73
90
86
71
74
70
89
74
70
71
71
76
79
76
75
66 ] )
