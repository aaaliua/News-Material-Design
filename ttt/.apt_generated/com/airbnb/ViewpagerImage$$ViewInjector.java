// Generated code from Butter Knife. Do not modify!
package com.airbnb;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ViewpagerImage$$ViewInjector {
  public static void inject(Finder finder, final com.airbnb.ViewpagerImage target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492964, "field 'mIndicator'");
    target.mIndicator = (com.aaaliua.view.CirclePageIndicator) view;
    view = finder.findRequiredView(source, 2131492965, "field 'background'");
    target.background = (android.widget.LinearLayout) view;
    view = finder.findRequiredView(source, 2131492963, "field 'pagers'");
    target.pagers = (com.aaaliua.view.HackyViewPager) view;
  }

  public static void reset(com.airbnb.ViewpagerImage target) {
    target.mIndicator = null;
    target.background = null;
    target.pagers = null;
  }
}
