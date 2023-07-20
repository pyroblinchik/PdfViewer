package com.pyroblinchik.pdf_viewer.viewer;

public interface CancellableTaskDefinition<Params, Result> {
    public Result doInBackground(Params... params);

    public void doCancel();

    public void doCleanup();
}
