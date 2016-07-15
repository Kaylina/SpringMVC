package com.elixir.common.until;

import java.math.BigDecimal;

/**
* @ClassName: BigDecimalUtil
* @Description:
* @author Jingyan
* @date 2016年3月21日 下午5:48:35
*/ 
public class BigDecimalUtil {

    public static synchronized int compareTo(BigDecimal source, BigDecimal dest) throws Exception {
        return source.compareTo(dest);
    }

    public static synchronized int compareTo(BigDecimal source, BigDecimal dest, int scale) throws Exception {
        return compareTo(source, dest, false, scale);
    }

    public static synchronized int compareTo(BigDecimal source, BigDecimal dest, boolean checked, int scale) throws Exception {

		return compareTo(source,dest,checked,scale, BigDecimal.ROUND_CEILING);

	}
	
	public static synchronized int compareTo(BigDecimal source,BigDecimal dest,boolean checked,int scale,int roundType) throws Exception {

		if(null==source || null==dest)
			throw new IllegalArgumentException();
		if(checked){
			BigDecimal theSource = source.setScale(scale, roundType);
            BigDecimal theDest = dest.setScale(scale, roundType);
            return theSource.compareTo(theDest);
        } else {
            return source.compareTo(dest);
        }

    }

    /**
     * 四舍五入
     * @param source
     * @return
     */
    public static synchronized BigDecimal halfUp(BigDecimal source){
        return source.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 加法四舍五入两位小数
     * @param source
     * @return
     */
    public static synchronized BigDecimal addHalfUp(BigDecimal source,BigDecimal dest){
    	if(dest!=null&&source!=null){
    		return source=source.add(dest);
    	}else if(source!=null){
    		return source;
    	}else{
    		return dest;
    	}
    }
    
    /**
     * 减法四舍五入两位小数
     * @param source
     * @return
     */
    public static synchronized BigDecimal subhalfUp(BigDecimal source,BigDecimal dest){
    	if(dest!=null&&source!=null){
    		return source.subtract(dest);
    	}else if(dest==null){
    		return source;
    	}else{
    		return new BigDecimal(0);
    	}
    }


    /**
     * 进位处理
     * @param source
     * @param scale
     * @return
     */
    public static synchronized BigDecimal up(BigDecimal source, int scale) {

        if (null == source) {
            return null;
        }
        source = new BigDecimal(source.stripTrailingZeros().toPlainString());
        if (source.scale() <= scale) {
            return source;
        }
        return source.setScale(scale, BigDecimal.ROUND_UP);
    }

    /**
     * 降位处理
     * @param source
     * @param scale
     * @return
     */
    public static synchronized BigDecimal down(BigDecimal source,int scale) {
        if (null == source) {
            return null;
        }
        source = source.stripTrailingZeros();
        if (source.scale() <= scale) {
            return source;
        }
        return source.setScale(scale, BigDecimal.ROUND_DOWN);
    }

    /**
     * 计算unit在源数据上的最大公约数
     *
     * @param source 源数据
     * @param unit
     * @return
     */
    public static int getGCM(double source, int unit) {
        int result = 0;
        double unitCount = source / unit;
        for (int i = 0; i <= unitCount; i++) {
            if (source >= unit) {
                source -= unit;
                result+=unit;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(new BigDecimal("10.555550000000").stripTrailingZeros().toPlainString());
        System.out.println((new BigDecimal("10.555550000000")).stripTrailingZeros().toPlainString());
        System.out.println(bigDecimal.setScale(2, BigDecimal.ROUND_UP));
    }

}
