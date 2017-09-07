package com.yw.animatorpath;

import android.animation.FloatArrayEvaluator;
import android.animation.TypeEvaluator;

/**
 * Created by yw on 2017/9/8.
 * 估值器：计算当前时间点对应的坐标
 */

public class PathEvaluator implements TypeEvaluator<PathPoint> {

    @Override
    public PathPoint evaluate(float t, PathPoint startValue, PathPoint endValue) {
        //t: 百分比 0 ~ 1
        float x, y; //任意时间点坐标
        if (endValue.mOperation == PathPoint.CUBIC) { //贝塞尔
            float oneMinusT = 1 - t;
            x = oneMinusT * oneMinusT * oneMinusT * startValue.getX() +
                    3 * oneMinusT * oneMinusT * t * endValue.mControl0X +
                    3 * oneMinusT * t * t * endValue.mControl1X +
                    t * t * t * endValue.getX();

            y = oneMinusT * oneMinusT * oneMinusT * startValue.getY() +
                    3 * oneMinusT * oneMinusT * t * endValue.mControl0Y +
                    3 * oneMinusT * t * t * endValue.mControl1Y +
                    t * t * t * endValue.getY();

        } else if (endValue.mOperation == PathPoint.LINE) { //直线运动
            // x, y = 起点 - t * (起始点和终点的距离)
            x = startValue.getX() + t * (endValue.getX() - startValue.getX());
            y = startValue.getY() + t * (endValue.getY() - startValue.getY());
        } else { //其他
            x = endValue.getX();
            y = endValue.getY();
        }
        return PathPoint.moveTo(x, y);
    }
}
