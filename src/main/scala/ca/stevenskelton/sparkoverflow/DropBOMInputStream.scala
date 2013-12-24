package ca.stevenskelton.sparkoverflow

import java.io.File

class DropBOMInputStream(file: File) extends java.io.FileInputStream(file) {
  var BOM = true;

  def skipBom: Unit = if (BOM) { BOM = false; read; read; read; }

  override def read(): Int = {
    skipBom
    super.read
  }
  override def read(b: Array[Byte]) = {
    skipBom
    super.read(b)
  }
  override def read(b: Array[Byte], off: Int, len: Int) = {
    skipBom
    super.read(b, off, len)
  }
}