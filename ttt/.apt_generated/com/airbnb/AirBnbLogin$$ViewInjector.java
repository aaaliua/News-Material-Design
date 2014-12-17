// Generated code from Butter Knife. Do not modify!
package com.airbnb;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AirBnbLogin$$ViewInjector {
  public static void inject(Finder finder, final com.airbnb.AirBnbLogin target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492957, "field 't'");
    target.t = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131492959, "field 'weibo' and method 'weibo'");
    target.weibo = view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.weibo(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492961, "field 'google'");
    target.google = view;
    view = finder.findRequiredView(source, 2131492962, "field 'facebook'");
    target.facebook = view;
    view = finder.findRequiredView(source, 2131492960, "field 'more' and method 'more'");
    target.more = view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.more(p0);
        }
      });
  }

  public static void reset(com.airbnb.AirBnbLogin target) {
    target.t = null;
    target.weibo = null;
    target.google = null;
    target.facebook = null;
    target.more = null;
  }
}
