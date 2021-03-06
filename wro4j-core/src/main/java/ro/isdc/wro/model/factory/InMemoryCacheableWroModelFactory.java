/**
 * Copyright wro4j@2011
 */
package ro.isdc.wro.model.factory;

import ro.isdc.wro.manager.WroManager;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.util.DestroyableLazyInitializer;


/**
 * Adds the ability to cache the model in memory. This class replace the schedulerAwareDecorator, because the scheduler
 * job is not done in decorator anymore, but rather in {@link WroManager}.
 *
 * @author Alex Objelean
 * @created 15 Oct 2011
 * @since 1.4.2
 */
public class InMemoryCacheableWroModelFactory extends WroModelFactoryDecorator {
  /**
   * Reference to cached model instance. Using volatile keyword fix the problem with double-checked locking in JDK 1.5.
   */
  private volatile WroModel model;

  public InMemoryCacheableWroModelFactory(final WroModelFactory decorated) {
    super(decorated);
  }

  private DestroyableLazyInitializer<WroModel> modelInitializer = new DestroyableLazyInitializer<WroModel>() {
    @Override
    protected WroModel initialize() {
      model = InMemoryCacheableWroModelFactory.super.create();
      return model;
    }
  };

  /**
   * {@inheritDoc}
   */
  @Override
  public WroModel create() {
    return modelInitializer.get();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void destroy() {
    modelInitializer.destroy();
  }
}
