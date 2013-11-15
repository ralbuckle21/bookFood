// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android.pojo;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CardInfo
{

	private String CardAbbrName;
	private String CardAdjust;
	private int CardAtk;
	private String CardAtk2;
	private String CardBagNum;
	private String CardCamp;
	private int CardDef;
	private String CardDef2;
	private String CardEfficeType;
	private int CardID;
	private int CardIsCZN;
	private int CardIsTKEN;
	private int CardIsYKDT;
	private int CardLover;
	private String CardOnceName;
	private String CardPass;
	private String CardPhAl;
	private int CardStarNum;
	private String CardUnion;
	private String ENCardAttribute;
	private String ENCardBan;
	private String ENCardDepict;
	private String ENCardName;
	private String ENCardName2;
	private String ENCardRace;
	private String ENCardRare;
	private String ENCardType;
	private String ENDCardType;
	private String JPCardAttribute;
	private String JPCardDepict;
	private String JPCardName;
	private String JPCardRace;
	private String JPCardType;
	private String JPDCardType;
	private String SCCardAttribute;
	private String SCCardBan;
	private String SCCardDepict;
	private String SCCardName;
	private String SCCardRace;
	private String SCCardRare;
	private String SCCardType;
	private String SCDCardType;
	private String TCCardAttribute;
	private String TCCardBan;
	private String TCCardDepict;
	private String TCCardName;
	private String TCCardRace;
	private String TCCardRare;
	private String TCCardType;
	private String TCDCardType;
	private int _id;

	public CardInfo()
	{
	}

	public String getCardAbbrName()
	{
		return CardAbbrName;
	}

	public String getCardAdjust()
	{
		return CardAdjust;
	}

	public int getCardAtk()
	{
		return CardAtk;
	}

	public String getCardAtk2()
	{
		return CardAtk2;
	}

	public String getCardBagNum()
	{
		return CardBagNum;
	}

	public String getCardCamp()
	{
		return CardCamp;
	}

	public int getCardDef()
	{
		return CardDef;
	}

	public String getCardDef2()
	{
		return CardDef2;
	}

	public String getCardEfficeType()
	{
		return CardEfficeType;
	}

	public int getCardID()
	{
		return CardID;
	}

	public int getCardIsCZN()
	{
		return CardIsCZN;
	}

	public int getCardIsTKEN()
	{
		return CardIsTKEN;
	}

	public int getCardIsYKDT()
	{
		return CardIsYKDT;
	}

	public int getCardLover()
	{
		return CardLover;
	}

	public String getCardOnceName()
	{
		return CardOnceName;
	}

	public String getCardPass()
	{
		return CardPass;
	}

	public String getCardPhAl()
	{
		return CardPhAl;
	}

	public int getCardStarNum()
	{
		return CardStarNum;
	}

	public String getCardUnion()
	{
		return CardUnion;
	}

	public String getENCardAttribute()
	{
		return ENCardAttribute;
	}

	public String getENCardBan()
	{
		return ENCardBan;
	}

	public String getENCardDepict()
	{
		return ENCardDepict;
	}

	public String getENCardName()
	{
		return ENCardName;
	}

	public String getENCardName2()
	{
		return ENCardName2;
	}

	public String getENCardRace()
	{
		return ENCardRace;
	}

	public String getENCardRare()
	{
		return ENCardRare;
	}

	public String getENCardType()
	{
		return ENCardType;
	}

	public String getENDCardType()
	{
		return ENDCardType;
	}

	public String getJPCardAttribute()
	{
		return JPCardAttribute;
	}

	public String getJPCardDepict()
	{
		return JPCardDepict;
	}

	public String getJPCardName()
	{
		return JPCardName;
	}

	public String getJPCardRace()
	{
		return JPCardRace;
	}

	public String getJPCardType()
	{
		return JPCardType;
	}

	public String getJPDCardType()
	{
		return JPDCardType;
	}

	public String getSCCardAttribute()
	{
		return SCCardAttribute;
	}

	public String getSCCardBan()
	{
		return SCCardBan;
	}

	public String getSCCardDepict()
	{
		return SCCardDepict;
	}

	public String getSCCardName()
	{
		return SCCardName;
	}

	public String getSCCardRace()
	{
		return SCCardRace;
	}

	public String getSCCardRare()
	{
		return SCCardRare;
	}

	public String getSCCardType()
	{
		return SCCardType;
	}

	public String getSCDCardType()
	{
		return SCDCardType;
	}

	public String getTCCardAttribute()
	{
		return TCCardAttribute;
	}

	public String getTCCardBan()
	{
		return TCCardBan;
	}

	public String getTCCardDepict()
	{
		return TCCardDepict;
	}

	public String getTCCardName()
	{
		return TCCardName;
	}

	public String getTCCardRace()
	{
		return TCCardRace;
	}

	public String getTCCardRare()
	{
		return TCCardRare;
	}

	public String getTCCardType()
	{
		return TCCardType;
	}

	public String getTCDCardType()
	{
		return TCDCardType;
	}

	public int get_id()
	{
		return _id;
	}

	public void setCardAbbrName(String s)
	{
		CardAbbrName = s;
	}

	public void setCardAdjust(String s)
	{
		CardAdjust = s;
	}

	public void setCardAtk(int i)
	{
		CardAtk = i;
	}

	public void setCardAtk2(String s)
	{
		CardAtk2 = s;
	}

	public void setCardBagNum(String s)
	{
		CardBagNum = s;
	}

	public void setCardCamp(String s)
	{
		CardCamp = s;
	}

	public void setCardDef(int i)
	{
		CardDef = i;
	}

	public void setCardDef2(String s)
	{
		CardDef2 = s;
	}

	public void setCardEfficeType(String s)
	{
		CardEfficeType = s;
	}

	public void setCardID(int i)
	{
		CardID = i;
	}

	public void setCardIsCZN(int i)
	{
		CardIsCZN = i;
	}

	public void setCardIsTKEN(int i)
	{
		CardIsTKEN = i;
	}

	public void setCardIsYKDT(int i)
	{
		CardIsYKDT = i;
	}

	public void setCardLover(int i)
	{
		CardLover = i;
	}

	public void setCardOnceName(String s)
	{
		CardOnceName = s;
	}

	public void setCardPass(String s)
	{
		CardPass = s;
	}

	public void setCardPhAl(String s)
	{
		CardPhAl = s;
	}

	public void setCardStarNum(int i)
	{
		CardStarNum = i;
	}

	public void setCardUnion(String s)
	{
		CardUnion = s;
	}

	public void setENCardAttribute(String s)
	{
		ENCardAttribute = s;
	}

	public void setENCardBan(String s)
	{
		ENCardBan = s;
	}

	public void setENCardDepict(String s)
	{
		ENCardDepict = s;
	}

	public void setENCardName(String s)
	{
		ENCardName = s;
	}

	public void setENCardName2(String s)
	{
		ENCardName2 = s;
	}

	public void setENCardRace(String s)
	{
		ENCardRace = s;
	}

	public void setENCardRare(String s)
	{
		ENCardRare = s;
	}

	public void setENCardType(String s)
	{
		ENCardType = s;
	}

	public void setENDCardType(String s)
	{
		ENDCardType = s;
	}

	public void setJPCardAttribute(String s)
	{
		JPCardAttribute = s;
	}

	public void setJPCardDepict(String s)
	{
		JPCardDepict = s;
	}

	public void setJPCardName(String s)
	{
		JPCardName = s;
	}

	public void setJPCardRace(String s)
	{
		JPCardRace = s;
	}

	public void setJPCardType(String s)
	{
		JPCardType = s;
	}

	public void setJPDCardType(String s)
	{
		JPDCardType = s;
	}

	public void setSCCardAttribute(String s)
	{
		SCCardAttribute = s;
	}

	public void setSCCardBan(String s)
	{
		SCCardBan = s;
	}

	public void setSCCardDepict(String s)
	{
		SCCardDepict = s;
	}

	public void setSCCardName(String s)
	{
		SCCardName = s;
	}

	public void setSCCardRace(String s)
	{
		SCCardRace = s;
	}

	public void setSCCardRare(String s)
	{
		SCCardRare = s;
	}

	public void setSCCardType(String s)
	{
		SCCardType = s;
	}

	public void setSCDCardType(String s)
	{
		SCDCardType = s;
	}

	public void setTCCardAttribute(String s)
	{
		TCCardAttribute = s;
	}

	public void setTCCardBan(String s)
	{
		TCCardBan = s;
	}

	public void setTCCardDepict(String s)
	{
		TCCardDepict = s;
	}

	public void setTCCardName(String s)
	{
		TCCardName = s;
	}

	public void setTCCardRace(String s)
	{
		TCCardRace = s;
	}

	public void setTCCardRare(String s)
	{
		TCCardRare = s;
	}

	public void setTCCardType(String s)
	{
		TCCardType = s;
	}

	public void setTCDCardType(String s)
	{
		TCDCardType = s;
	}

	public void set_id(int i)
	{
		_id = i;
	}

	public String toString()
	{
		/*String s;
		int i;
		int j;
		s = "";
		Class class1;
		Field afield[];
		Field field;
		Method method = null;
		String s1;
		try
		{
			class1 = getClass();
			afield = class1.getDeclaredFields();
			i = afield.length;
		}
		catch (Exception exception)
		{
			return s;
		}
		j = 0;*/
		  /*goto _L1
_L6:
		field = afield[j];
		method = class1.getMethod((new StringBuilder("get")).append(field.getName()).toString(), new Class[0]);
		if (!field.getType().getName().contains("String")) goto _L3; else goto _L2
_L2:
		s = (new StringBuilder(String.valueOf(s))).append(field.getName()).append(": ").append((String)method.invoke(this, new Object[0])).toString();
_L4:
		s = (new StringBuilder(String.valueOf(s))).append("\n").toString();
		j++;
		continue; 
_L3:
		s1 = (new StringBuilder(String.valueOf(s))).append(field.getName()).append(": ").append(((Integer)method.invoke(this, new Object[0])).toString()).toString();
		s = s1;
		  goto _L4
_L1:
		if (j < i) goto _L6; else goto _L5
_L5:
		return s;*/
		/*for(j=0;j<i;j++){
			field = afield[j];
			try {
				method = class1.getMethod((new StringBuilder("get")).append(field.getName()).toString(), new Class[0]);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!field.getType().getName().contains("String")){
				try {
					s1 = (new StringBuilder(String.valueOf(s))).append(field.getName()).append(": ").append(((Integer)method.invoke(this, new Object[0])).toString()).toString();
					s = s1;
					s = (new StringBuilder(String.valueOf(s))).append("\n").toString();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					s = (new StringBuilder(String.valueOf(s))).append(field.getName()).append(": ").append((String)method.invoke(this, new Object[0])).toString();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return s;
	}*/
		Object localObject = "";
	    int i;
	    int j;
	    do
	      try
	      {
	        Class localClass = getClass();
	        Field[] arrayOfField = localClass.getDeclaredFields();
	        i = arrayOfField.length;
	        j = 0;
	        //continue;
	        Field localField = arrayOfField[j];
	        Method localMethod = localClass.getMethod("get" + localField.getName(), new Class[0]);
	        if (localField.getType().getName().contains("String"));
	        String str;
	        for (localObject = localObject + localField.getName() + ": " + (String)localMethod.invoke(this, new Object[0]); ; localObject = str)
	        {
	          localObject = localObject + "\n";
	          j++;
	          //break;
	          str = localObject + localField.getName() + ": " + ((Integer)localMethod.invoke(this, new Object[0])).toString();
	        }
	      }
	      catch (Exception localException)
	      {
	        return (String)localObject;
	      }
	    while (j < i);
	    //return (String)localObject;
	}
}