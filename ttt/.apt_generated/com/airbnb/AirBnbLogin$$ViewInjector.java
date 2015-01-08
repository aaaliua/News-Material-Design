// Generated code from Butter Knife. Do not modify!
package com.airbnb;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AirBnbLogin$$ViewInjector {
  public static void inject(Finder finder, final com.airbnb.AirBnbLogin target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492972, "field 'google'");
    target.google = view;
    view = finder.findRequiredView(source, 2131492970, "field 'weibo' and method 'weibo'");
    target.weibo = view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.weibo(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492971, "field 'more' and method 'more'");
    target.more = view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.more(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492973, "field 'facebook'");
    target.facebook = view;
    view = finder.findRequiredView(source, 2131492968, "field 't'");
    target.t = (android.widget.TextView) view;
  }

  public static void reset(com.airbnb.AirBnbLogin target) {
    target.google = null;
    target.weibo = null;
    target.more = null;
    target.facebook = null;
    target.t = null;
  }
}
